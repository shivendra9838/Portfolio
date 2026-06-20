$(document).ready(function(){
    let revealObserver;

    function observeRevealTargets() {
        if (!revealObserver) {
            revealObserver = new IntersectionObserver((entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add('is-visible');
                    }
                });
            }, { threshold: 0.15 });
        }

        document.querySelectorAll('.reveal-on-scroll, .container, .footer .box').forEach((el) => {
            revealObserver.observe(el);
        });
    }

    // Navbar toggle for mobile
    $('#menu').click(function(){
        $(this).toggleClass('fa-times');
        $('.navbar').toggleClass('nav-toggle');
    });

    // Scroll and load event for navbar and scroll-top button
    $(window).on('scroll load', function(){
        $('#menu').removeClass('fa-times');
        $('.navbar').removeClass('nav-toggle');
        if(window.scrollY > 60){
            document.querySelector('#scroll-top').classList.add('active');
        } else {
            document.querySelector('#scroll-top').classList.remove('active');
        }

        const scrollable = document.documentElement.scrollHeight - window.innerHeight;
        const progress = scrollable > 0 ? (window.scrollY / scrollable) * 100 : 0;
        $('#scroll-progress').css('width', `${progress}%`);
    });

    // Data for achievements and certifications
    const experienceData = [
        {
            type: 'achievement',
            title: 'Participant in Quiz Competition by GeeksforGeeks',
            description: 'Secured 1st position in the quiz',
            date: 'August 2024',
            details: 'Demonstrated strong problem-solving skills and secured 1st position in the quiz competition.'
        },
        {
            type: 'achievement',
            title: 'Participant in Hack-A-Throne by GeeksforGeeks',
            description: 'Top 5 position among 150+ teams on GeeksforGeeks',
            date: 'August 2024',
            details: 'Collaborated in a competitive hackathon environment and ranked in the top 5 among 150+ teams.'
        },
        {
            type: 'certification',
            title: 'HackerRank 5-Star in C++',
            description: 'Demonstrated strong problem-solving skills and proficiency in programming',
            date: 'March 25',
            details: 'Earned 5-star recognition in C++ on HackerRank, showing programming fundamentals and problem-solving ability.'
        }
    ];
    // Function to render timeline items
    function renderTimeline(filter = 'all') {
        const timeline = $('#timeline');
        timeline.empty(); // Clear existing content
        experienceData.forEach((item, index) => {
            if (filter === 'all' || item.type === filter) {
                // Alternate left and right containers for visual appeal
                const side = index % 2 === 0 ? 'left' : 'right';
                const certificateButton = item.certificateLink ? `
                                <div class="cert-wrapper">
                                    <a href="${item.certificateLink}" target="_blank" rel="noopener noreferrer" class="cert-btn">
                                      <i class="fas fa-certificate"></i> View Certificate
                                    </a>
                                </div>` : '';
                const certifiedBadge = item.certificateLink ? `<span class="certified-badge">✓ Certified</span>` : '';
                const html = `
                    <div class="container ${side}" data-type="${item.type}">
                        ${certifiedBadge}
                        <div class="content">
                            <div class="tag">
                                <h2>${item.title}</h2>
                            </div>
                            <div class="desc">
                                <h3>${item.description}</h3>
                                <p>${item.date}</p>
                                <div class="details hidden">${item.details}</div>
                                <button class="toggle-details">Show Details</button>
                                ${certificateButton}
                            </div>
                        </div>
                    </div>
                `;
                timeline.append(html);
            }
        });

        // Re-apply ScrollReveal animations after rendering
        ScrollReveal().reveal('.experience .timeline .container', { interval: 400 });
        observeRevealTargets();
    }

    // Initial render of timeline
    renderTimeline();

    // Filter button click handler
    $('.filter-btn').click(function() {
        $('.filter-btn').removeClass('active');
        $(this).addClass('active');
        const filter = $(this).data('filter');
        renderTimeline(filter);
    });

    // Toggle details on click
    $(document).on('click', '.toggle-details', function() {
        const details = $(this).siblings('.details');
        if (details.hasClass('hidden')) {
            details.removeClass('hidden').slideDown(300);
            $(this).text('Hide Details');
        } else {
            details.addClass('hidden').slideUp(300);
            $(this).text('Show Details');
        }
    });

    /* ===== SCROLL REVEAL ANIMATION ===== */
    const srtop = ScrollReveal({
        origin: 'top',
        distance: '80px',
        duration: 1000,
        reset: true
    });

    /* SCROLL EXPERIENCE */
    srtop.reveal('.experience .timeline', { delay: 400 });
    observeRevealTargets();

    // Start of Tawk.to Live Chat
    var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
    (function(){
        var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
        s1.async = true;
        s1.src = 'https://embed.tawk.to/60df10bf7f4b000ac03ab6a8/1f9jlirg6';
        s1.charset = 'UTF-8';
        s1.setAttribute('crossorigin', '*');
        s0.parentNode.insertBefore(s1, s0);
    })();
    // End of Tawk.to Live Chat

    // Disable developer mode
    document.onkeydown = function(e) {
        if (e.keyCode == 123) return false;
        if (e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) return false;
        if (e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) return false;
        if (e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) return false;
        if (e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) return false;
    };

    // Dynamic favicon and title on tab visibility
    document.addEventListener('visibilitychange', function() {
        if (document.visibilityState === "visible") {
            document.title = "Experience | Portfolio";
            $("#favicon").attr("href", "/assets/images/hero (2).png");
        } else {
            document.title = "Back To Portfolio";
            $("#favicon").attr("href", "/assets/images/favhand.png");
        }
    });
});
