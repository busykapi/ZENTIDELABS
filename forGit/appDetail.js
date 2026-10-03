document.addEventListener("DOMContentLoaded", () => {

    let currentApp = null;
    let currentScreen = 0;
    let touchStartX = 0;

    const loading = document.getElementById("appLoading");
    const error = document.getElementById("appError");
    const detail = document.getElementById("appDetail");

    const screenContainer =
        document.getElementById("screenContainer");

    const dotsContainer =
        document.getElementById("screenDots");

    const currentCounter =
        document.getElementById("screenCurrent");

    const totalCounter =
        document.getElementById("screenTotal");


    /* ========================================
       앱 데이터 불러오기
    ======================================== */

    async function loadApp() {

        try {

            const params =
                new URLSearchParams(window.location.search);

            const appId = params.get("id");

            if (!appId) {
                showError();
                return;
            }


            const response =
                await fetch("forGit/apps.json");


            if (!response.ok) {
                throw new Error("apps.json load failed");
            }


            const apps = await response.json();


            currentApp =
                apps.find(app => app.id === appId);


            if (!currentApp) {
                showError();
                return;
            }


            renderApp();

        } catch (e) {

            console.error(e);

            showError();
        }

    }


    /* ========================================
       화면 렌더링
    ======================================== */

    function renderApp() {

        document.title =
            `${currentApp.name} | ZENTIDE LABS`;


        document.getElementById("appName")
            .textContent =
            currentApp.name || "";


        document.getElementById("appSubTitle")
            .textContent =
            currentApp.subtitle || "";


        document.getElementById("appDescription")
            .textContent =
            currentApp.description || "";


        document.getElementById("appPlatform")
            .textContent =
            currentApp.platform || "-";


        document.getElementById("appCategory")
            .textContent =
            currentApp.category || "-";


        document.getElementById("appStatus")
            .textContent =
            currentApp.status || "-";


        /* 아이콘 */

        const icon =
            document.getElementById("appIcon");

        icon.src = currentApp.icon || "";
        icon.alt =
            `${currentApp.name || "App"} icon`;


        /* TECH */

        const techContainer =
            document.getElementById("appTech");

        techContainer.innerHTML = "";

        (currentApp.tech || []).forEach(item => {

            const tag =
                document.createElement("span");

            tag.className = "tech-tag";
            tag.textContent = item;

            techContainer.appendChild(tag);

        });


        /* FEATURES */

        const featureContainer =
            document.getElementById("appFeatures");

        featureContainer.innerHTML = "";

        (currentApp.features || []).forEach(item => {

            const li =
                document.createElement("li");

            li.textContent = item;

            featureContainer.appendChild(li);

        });


        /* STORE */

        setStoreLink(
            "googlePlayLink",
            currentApp.googlePlay
        );

        setStoreLink(
            "appStoreLink",
            currentApp.appStore
        );


        /* 스크린샷 */

        renderScreens();


        loading.hidden = true;
        error.hidden = true;
        detail.hidden = false;

    }


    /* ========================================
       STORE LINK
    ======================================== */

    function setStoreLink(id, url) {

        const element =
            document.getElementById(id);

        if (url) {

            element.href = url;
            element.hidden = false;

        } else {

            element.hidden = true;

        }

    }


    /* ========================================
       SCREENSHOT 생성
    ======================================== */

    function renderScreens() {

        screenContainer.innerHTML = "";
        dotsContainer.innerHTML = "";

        currentScreen = 0;


        const screens =
            currentApp.screens || [];


        totalCounter.textContent =
            screens.length;


        if (screens.length === 0) {

            screenContainer.innerHTML =
                `<div class="app-message">
                    Preview 준비중
                 </div>`;

            currentCounter.textContent = "0";

            return;
        }


        screens.forEach((src, index) => {

            /* 이미지 */

            const img =
                document.createElement("img");

            img.src = src;

            img.alt =
                `${currentApp.name} screenshot ${index + 1}`;

            img.loading =
                index === 0 ? "eager" : "lazy";

            img.className =
                index === 0
                    ? "app-screen active"
                    : "app-screen";

            screenContainer.appendChild(img);


            /* DOT */

            const dot =
                document.createElement("button");

            dot.type = "button";

            dot.className =
                index === 0
                    ? "screen-dot active"
                    : "screen-dot";

            dot.setAttribute(
                "aria-label",
                `Screenshot ${index + 1}`
            );

            dot.addEventListener("click", () => {

                currentScreen = index;

                updateScreen();

            });


            dotsContainer.appendChild(dot);

        });


        currentCounter.textContent = "1";

    }


    /* ========================================
       SCREEN 변경
    ======================================== */

    function updateScreen() {

        const screens =
            document.querySelectorAll(".app-screen");

        const dots =
            document.querySelectorAll(".screen-dot");


        if (!screens.length)
            return;


        screens.forEach((screen, index) => {

            screen.classList.toggle(
                "active",
                index === currentScreen
            );

        });


        dots.forEach((dot, index) => {

            dot.classList.toggle(
                "active",
                index === currentScreen
            );

        });


        currentCounter.textContent =
            currentScreen + 1;

    }


    function nextScreen() {

        const length =
            currentApp?.screens?.length || 0;


        if (!length)
            return;


        currentScreen =
            (currentScreen + 1) % length;


        updateScreen();

    }


    function prevScreen() {

        const length =
            currentApp?.screens?.length || 0;


        if (!length)
            return;


        currentScreen =
            (currentScreen - 1 + length) % length;


        updateScreen();

    }


    /* ========================================
       버튼
    ======================================== */

    document
        .getElementById("screenNext")
        .addEventListener(
            "click",
            nextScreen
        );


    document
        .getElementById("screenPrev")
        .addEventListener(
            "click",
            prevScreen
        );


    /* ========================================
       키보드
    ======================================== */

    document.addEventListener(
        "keydown",
        e => {

            if (e.key === "ArrowRight")
                nextScreen();

            if (e.key === "ArrowLeft")
                prevScreen();

        }
    );


    /* ========================================
       모바일 SWIPE
    ======================================== */

    screenContainer.addEventListener(
        "touchstart",
        e => {

            touchStartX =
                e.changedTouches[0].screenX;

        },
        { passive: true }
    );


    screenContainer.addEventListener(
        "touchend",
        e => {

            const touchEndX =
                e.changedTouches[0].screenX;

            const diff =
                touchStartX - touchEndX;


            if (Math.abs(diff) < 50)
                return;


            if (diff > 0)
                nextScreen();
            else
                prevScreen();

        },
        { passive: true }
    );


    /* ========================================
       ERROR
    ======================================== */

    function showError() {

        loading.hidden = true;
        detail.hidden = true;
        error.hidden = false;

    }


    loadApp();

});