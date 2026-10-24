// Shared code used by every page.

// One place that talks to the Spring Boot API.
async function api(path, options) {
  const res = await fetch(path, Object.assign({ headers: { "Content-Type": "application/json" } }, options));
  if (!res.ok) {
    let err = {};
    try { err = await res.json(); } catch (e) { /* no JSON body */ }
    const details = err.errors ? Object.values(err.errors).join(", ") : err.message;
    throw new Error(details || "Request failed (" + res.status + ")");
  }
  return res.status === 204 ? null : res.json();
}

// "address.street" -> item.address.street
function getPath(obj, path) {
  const v = path.split(".").reduce((o, k) => (o == null ? null : o[k]), obj);
  return v == null ? "" : v;
}

function setPath(obj, path, value) {
  const keys = path.split(".");
  keys.slice(0, -1).forEach(k => { obj[k] = obj[k] || {}; obj = obj[k]; });
  obj[keys[keys.length - 1]] = value;
}

// Builds a form + table for one resource from a small settings object.
function crudPage(cfg) {
  const form = document.getElementById("form");
  const rows = document.getElementById("rows");
  const msg = document.getElementById("msg");
  const title = document.getElementById("formTitle");
  const empty = document.getElementById("empty");
  const inputs = {};
  let parentSel = null;
  let editId = null;
  const cap = cfg.name.charAt(0).toUpperCase() + cfg.name.slice(1);

  function show(text, type) { msg.textContent = text; msg.className = "msg " + type; }
  function friendly(e) {
    return e instanceof TypeError ? "Cannot reach the server. Start the Spring Boot app and refresh." : e.message;
  }

  // table header
  const head = document.getElementById("head");
  cfg.columns.forEach(c => {
    const th = document.createElement("th");
    th.textContent = c.label;
    head.appendChild(th);
  });
  head.appendChild(document.createElement("th"));

  // form controls
  if (cfg.parent) {
    parentSel = document.createElement("select");
    parentSel.setAttribute("aria-label", cfg.parent.label);
    form.appendChild(parentSel);
  }
  cfg.fields.forEach(f => {
    const input = document.createElement("input");
    input.placeholder = f.label;
    input.setAttribute("aria-label", f.label);
    if (f.type) input.type = f.type;
    form.appendChild(input);
    inputs[f.key] = input;
  });
  const saveBtn = document.createElement("button");
  saveBtn.type = "submit";
  const cancelBtn = document.createElement("button");
  cancelBtn.type = "button";
  cancelBtn.className = "plain";
  cancelBtn.textContent = "Cancel";
  form.append(saveBtn, cancelBtn);

  function resetForm() {
    editId = null;
    form.reset();
    title.textContent = "Add a " + cfg.name;
    saveBtn.textContent = "Save " + cfg.name;
    cancelBtn.style.display = "none";
    if (parentSel) parentSel.disabled = false;
  }

  function startEdit(item) {
    editId = item[cfg.idKey];
    cfg.fields.forEach(f => { inputs[f.key].value = getPath(item, f.key); });
    title.textContent = "Edit " + cfg.name + " " + editId;
    saveBtn.textContent = "Save changes";
    cancelBtn.style.display = "inline-block";
    if (parentSel) parentSel.disabled = true;
    window.scrollTo(0, 0);
  }

  async function remove(item) {
    const id = item[cfg.idKey];
    if (!confirm("Delete " + cfg.name + " " + id + "? " + (cfg.deleteWarning || ""))) return;
    try {
      await api(cfg.itemUrl(id), { method: "DELETE" });
      show(cap + " deleted", "ok");
      load();
    } catch (err) { show(friendly(err), "error"); }
  }

  function makeRow(item) {
    const tr = document.createElement("tr");
    cfg.columns.forEach(c => {
      const td = document.createElement("td");
      td.textContent = getPath(item, c.key);
      tr.appendChild(td);
    });
    const td = document.createElement("td");
    td.className = "actions";
    const edit = document.createElement("button");
    edit.className = "plain";
    edit.textContent = "Edit";
    edit.onclick = () => startEdit(item);
    const del = document.createElement("button");
    del.className = "danger";
    del.textContent = "Delete";
    del.onclick = () => remove(item);
    td.append(edit, del);
    tr.appendChild(td);
    return tr;
  }

  async function load() {
    try {
      if (parentSel) {
        const parents = await api(cfg.parent.url);
        const chosen = parentSel.value;
        parentSel.innerHTML = "";
        parents.forEach(p => parentSel.add(new Option(p[cfg.parent.labelKey], p[cfg.parent.idKey])));
        if (chosen) parentSel.value = chosen;
      }
      const data = await api(cfg.listUrl);
      rows.innerHTML = "";
      empty.style.display = data.length ? "none" : "block";
      data.forEach(item => rows.appendChild(makeRow(item)));
    } catch (err) { show(friendly(err), "error"); }
  }

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const wasEditing = editId !== null;
    if (!wasEditing && parentSel && !parentSel.value) {
      show("Add a " + cfg.parent.label.toLowerCase() + " first.", "error");
      return;
    }
    const body = {};
    cfg.fields.forEach(f => {
      const v = inputs[f.key].value;
      setPath(body, f.key, f.type === "number" ? Number(v) : v);
    });
    try {
      const url = wasEditing ? cfg.itemUrl(editId) : cfg.createUrl(parentSel ? parentSel.value : null);
      await api(url, { method: wasEditing ? "PUT" : "POST", body: JSON.stringify(body) });
      show(wasEditing ? "Changes saved" : cap + " saved", "ok");
      resetForm();
      load();
    } catch (err) { show(friendly(err), "error"); }
  });

  cancelBtn.onclick = resetForm;
  resetForm();
  load();
}
