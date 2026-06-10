$(document).ready(function () {

    $('#menu').click(function () {
        $(this).toggleClass('fa-times');
        $('.navbar').toggleClass('nav-toggle');
    });

    $(window).on('scroll load', function () {
        $('#menu').removeClass('fa-times');
        $('.navbar').removeClass('nav-toggle');

        if (window.scrollY > 60) {
            document.querySelector('#scroll-top').classList.add('active');
        } else {
            document.querySelector('#scroll-top').classList.remove('active');
        }
    });
});

document.addEventListener('visibilitychange',
    function () {
        if (document.visibilityState === "visible") {
            document.title = "Projects | Portfolio Shivendra Tiwari";
            $("#favicon").attr("href", "/assets/images/hero (2).png");
        }
        else {
            document.title = "Come Back To Portfolio";
            $("#favicon").attr("href", "/assets/images/favhand.png");
        }
    });


// fetch projects start
function getProjects() {
    return fetch("projects.json")
        .then(response => response.json())
        .then(data => {
            return data
        });
}


function showProjects(projects) {
    let projectsContainer = document.querySelector(".work .box-container");
    let projectsHTML = "";
    projects.forEach((project, index) => {
        projectsHTML += `
        <div class="grid-item ${project.category}">
        <div class="box tilt" style="width: 380px; margin: 1rem">
      <img draggable="false" src="/assets/images/projects/${project.image}.png" alt="project" />
      <div class="content">
        <div class="tag">
        <h3>${project.name}</h3>
        </div>
        <div class="desc">
          <p>${project.desc}</p>
          <div class="btns">
            <a href="${project.links.view}" class="btn" target="_blank"><i class="fas fa-eye"></i> View</a>
            <button class="btn details-btn" data-index="${index}"><i class="fas fa-info-circle"></i> Overview</button>
            <a href="${project.links.code}" class="btn" target="_blank">Code <i class="fas fa-code"></i></a>
          </div>
        </div>
      </div>
    </div>
    </div>`
    });
    projectsContainer.innerHTML = projectsHTML;

    if (!document.querySelector("#project-modal")) {
        document.body.insertAdjacentHTML("beforeend", `
<div id="project-modal" style="display:none; position:fixed; inset:0; background:rgba(0,0,0,0.7);
  z-index:9999; overflow-y:auto; padding:2rem;">
  <div id="modal-inner" style="background:#fff; border-radius:20px; max-width:700px;
    margin:2rem auto; padding:2.5rem; position:relative; font-family:'Poppins',sans-serif;">
    <button id="modal-close" style="position:absolute;top:1.5rem;right:1.5rem;
      background:none;border:none;font-size:2rem;cursor:pointer;color:#6b7280;">✕</button>
    <div id="modal-content"></div>
  </div>
</div>`);
    }

    function closeProjectModal() {
        $("#project-modal").hide();
        $("body").css("overflow", "auto");
    }

    $(".details-btn").on("click", function () {
        const project = projects[$(this).data("index")];
        $("#modal-content").html(`
<div style="display:flex;align-items:center;gap:1rem;margin-bottom:1.5rem;">
  <img src="/assets/images/projects/${project.image}.png"
    style="width:80px;height:60px;object-fit:cover;border-radius:10px;" alt="${project.name}">
  <div>
    <h2 style="font-size:2rem;font-weight:700;color:#1f2937;margin:0;">${project.name}</h2>
    <p style="color:#6b7280;font-size:1.3rem;margin:0.3rem 0 0;">${project.overview}</p>
  </div>
</div>

<div style="margin-bottom:1.5rem;">
  <h3 style="font-size:1.4rem;font-weight:600;color:#9333ea;margin-bottom:0.8rem;">
    <i class="fas fa-layer-group"></i> Tech Stack
  </h3>
  <div style="display:flex;flex-wrap:wrap;gap:0.6rem;">
    ${project.stack.map(s => `<span style="background:#f3e8ff;color:#7e22ce;padding:0.3rem 1rem;
      border-radius:20px;font-size:1.2rem;font-weight:600;">${s}</span>`).join('')}
  </div>
</div>

<div style="margin-bottom:2rem;">
  <h3 style="font-size:1.4rem;font-weight:600;color:#9333ea;margin-bottom:0.8rem;">
    <i class="fas fa-check-circle"></i> Key Features
  </h3>
  <ul style="padding-left:1.5rem;list-style:none;">
    ${project.features.map(f => `<li style="color:#374151;font-size:1.3rem;line-height:2;
      display:flex;align-items:center;gap:0.5rem;">
      <i class="fas fa-arrow-right" style="color:#9333ea;font-size:1rem;"></i>${f}</li>`).join('')}
  </ul>
</div>

<div style="display:flex;gap:1rem;justify-content:center;flex-wrap:wrap;">
  <a href="${project.links.view}" target="_blank"
    style="background:#9333ea;color:#fff;padding:1rem 2.5rem;border-radius:10px;
    font-size:1.4rem;font-weight:600;text-decoration:none;display:flex;align-items:center;gap:0.5rem;">
    <i class="fas fa-eye"></i> Live Demo
  </a>
  <a href="${project.links.code}" target="_blank"
    style="background:#1f2937;color:#fff;padding:1rem 2.5rem;border-radius:10px;
    font-size:1.4rem;font-weight:600;text-decoration:none;display:flex;align-items:center;gap:0.5rem;">
    <i class="fab fa-github"></i> GitHub
  </a>
</div>`);
        $("#project-modal").css("display", "block");
        $("body").css("overflow", "hidden");
    });

    $("#modal-close").on("click", closeProjectModal);
    $("#project-modal").on("click", function (e) {
        if (e.target === this) {
            closeProjectModal();
        }
    });

    // vanilla tilt.js
    // VanillaTilt.init(document.querySelectorAll(".tilt"), {
    //     max: 20,
    // });
    // // vanilla tilt.js  

    // /* ===== SCROLL REVEAL ANIMATION ===== */
    // const srtop = ScrollReveal({
    //     origin: 'bottom',
    //     distance: '80px',
    //     duration: 1000,
    //     reset: true
    // });

    // /* SCROLL PROJECTS */
    // srtop.reveal('.work .box', { interval: 200 });

    // isotope filter products
    var $grid = $('.box-container').isotope({
        itemSelector: '.grid-item',
        layoutMode: 'fitRows',
        masonry: {
            columnWidth: 200
        }
    });

    // filter items on button click
    $('.button-group').on('click', 'button', function () {
        $('.button-group').find('.is-checked').removeClass('is-checked');
        $(this).addClass('is-checked');
        var filterValue = $(this).attr('data-filter');
        $grid.isotope({ filter: filterValue });
    });
}

getProjects().then(data => {
    showProjects(data);
})
// fetch projects end

// Start of Tawk.to Live Chat
var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
(function () {
    var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
    s1.async = true;
    s1.src = 'https://embed.tawk.to/60df10bf7f4b000ac03ab6a8/1f9jlirg6';
    s1.charset = 'UTF-8';
    s1.setAttribute('crossorigin', '*');
    s0.parentNode.insertBefore(s1, s0);
})();
// End of Tawk.to Live Chat

// disable developer mode
document.onkeydown = function (e) {
    if (e.keyCode == 123) {
        return false;
    }
    if (e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
        return false;
    }
    if (e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
        return false;
    }
    if (e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
        return false;
    }
    if (e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
        return false;
    }
}
