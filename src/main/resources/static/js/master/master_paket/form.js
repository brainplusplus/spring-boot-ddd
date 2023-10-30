var min = new Date().getFullYear(),
        max = min + 10,
        select = document.getElementById('cmb_tahun');

for (var i = min; i <= max; i++) {
    var opt = document.createElement('option');
    opt.value = i;
    opt.innerHTML = i;
    select.appendChild(opt);
}