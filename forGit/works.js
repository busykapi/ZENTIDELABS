let slideIndex = 0;
let slideIndex1 = 1;
let slideIndex2 = 1;
let timer;
window.addEventListener("load", function() {
    showSlidesAuto();
});

// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

/*추가 앱 슬라이드*/
// Next1/previous1 controls
function plusSlides1(n) {
  showSlides1(slideIndex1 += n);
  n > 0 ? nextPage() : prevPage();
}

// Next2/previous2 controls
function plusSlides2(n) {
  showSlides2(slideIndex2 += n);
  n > 0 ? nextPage2() : prevPage2();
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  clearTimeout(timer);
}

/*추가 앱 슬라이드*/
/*Next1/previous1 controls*/
function showSlides1(n) {

  let slides1 = document.getElementsByClassName("mySlides1");

  if (n > slides1.length) {slideIndex1 = 1}
  if (n < 1) {slideIndex1 = slides1.length}
  for (let j = 0; j < slides1.length; j++) {
    slides1[j].style.display = "none";
  }

  slides1[slideIndex1-1].style.display = "block";
}

/*Next2/previous2 controls*/
function showSlides2(n) {

  let slides2 = document.getElementsByClassName("mySlides2");

  if (n > slides2.length) {slideIndex2 = 1}
  if (n < 1) {slideIndex2 = slides2.length}
  for (let k = 0; k < slides2.length; k++) {
    slides2[k].style.display = "none";
  }

  slides2[slideIndex2-1].style.display = "block";
}


function showSlidesAuto() {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}
  slides[slideIndex-1].style.display = "block";
  timer = setTimeout(showSlidesAuto, 5000); // Change image every 2 seconds
}



/*추가*/
let apps = [];
let apps2 = [];
let currentPage = 0;
let currentPage2 = 0;

const grid = document.getElementById("appGrid");
const grid2 = document.getElementById("appGrid2");


function getPageSize() {
  const w = window.innerWidth;

  // 모바일/태블릿: 3x2 = 6개
  if (w <= 1024) return 6;

  // PC: 5x2 = 10개
  return 10;
}


if (!grid){console.log("실패")}

async function initApps() {
  const res = await fetch("/apps.json");
  apps = await res.json(); // 배열
  renderApps();
}

async function initApps2() {
  const res2 = await fetch("/apps2.json");
  apps2 = await res2.json(); // 배열
  renderApps2();
}

function renderApps() {
  if (!grid) return;

  const pageSize = getPageSize();
  const start = currentPage * pageSize;
  const end = start + pageSize;
  const pageApps = apps.slice(start, end);

  grid.innerHTML = pageApps.map(app => `
    <a class="item" href="detail.html?id=${encodeURIComponent(app.id)}">
      <div class="circle">
        <img src="${app.icon}" alt="${app.name}">
      </div>
      <p class="name">${app.name}</p>
    </a>
  `).join("");
}

function renderApps2() {
  if (!grid2) return;

  const pageSize2 = getPageSize();
  const start2 = currentPage2 * pageSize2;
  const end2 = start2 + pageSize2;
  const pageApps2 = apps2.slice(start2, end2);

  grid2.innerHTML = pageApps2.map(app2 => `
    <a class="item" href="detail.html?id=${encodeURIComponent(app2.id)}">
      <div class="circle">
        <img src="${app2.icon}" alt="${app2.name}">
      </div>
      <p class="name">${app2.name}</p>
    </a>
  `).join("");
}


// 슬라이드 화살표랑 연결
function nextPage() {
  const totalPages = Math.ceil(apps.length / getPageSize());
  if (currentPage < totalPages - 1) {
    currentPage++;
    renderApps();
  }
}

function prevPage() {
  if (currentPage > 0) {
    currentPage--;
    renderApps();
  }
}

function nextPage2() {
  const totalPages2 = Math.ceil(apps.length / getPageSize());
  if (currentPage2 < totalPages2 - 1) {
    currentPage2++;
    renderApps2();
  }
}

function prevPage2() {
  if (currentPage2 > 0) {
    currentPage2--;
    renderApps2();
  }
}


window.addEventListener("DOMContentLoaded", async () => {
  // 1) 앱 데이터 로드 & 첫 페이지 렌더
  await initApps();
  await initApps2();

  // 2) 앱 슬라이드/섹션 첫 화면 보이게
  showSlides1(1);
  showSlides2(1);

});


let resizeTimer;
window.addEventListener("resize", () => {
  clearTimeout(resizeTimer);
  resizeTimer = setTimeout(() => {
    currentPage = 0;
    currentPage2 = 0;
    renderApps();
    renderApps2();
  }, 150);
});


