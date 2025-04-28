$(document).ready(function(){
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
    });

    // Data for achievements and certifications
    const experienceData = [
        {
            type: 'achievement',
            title: 'Participant In Quiz Competition By GeeksforGeeks',
            description: 'Secured 1st Position In The Quiz',
            date: 'August 2024',
            details: 'Competed against 100+ participants and demonstrated strong problem-solving skills.'
        },
        {
            type: 'achievement',
            title: 'Participant In Hack-A-Throne By GeeksforGeeks',
            description: 'Top 5 Position Among 150+ Teams',
            date: 'August 2024',
            details: 'Collaborated with a team to develop an innovative solution, ranking in the top 5.'
        },
        {
            type: 'achievement',
            title: 'Summer Project: The Complete MYSQL Developer Course— LPU',
            description: 'Summer Training | Open Source Program',
            date: 'June 2024 - August 2024',
            details: 'Developed a database-driven application using MySQL, contributing to open-source projects.'
        },
        {
            type: 'certification',
            title: 'Leadership Through Social Influence (Coursera)',
            description: 'Completed Course',
            date: 'December 2024',
            details: 'Learned strategies for effective leadership and social influence in professional settings.'
        },
        {
            type: 'certification',
            title: 'Building Web Applications in PHP (Coursera)',
            description: 'Completed Course',
            date: 'March 2023',
            details: 'Mastered PHP for building dynamic web applications, including database integration.'
        },
        {
            type: 'certification',
            title: 'Explore a Career In Front-End Web Development (LinkedIn)',
            description: 'Completed Course',
            date: 'February 2023',
            details: 'Gained insights into front-end development tools and career paths.'
        },
        {
            type: 'certification',
            title: 'HTML, CSS, and Javascript for Web Developer',
            description: 'Completed Course',
            date: 'April 2023',
            details: 'Developed proficiency in core web development technologies.'
        },
        {
            type: 'certification',
            title: 'Server-Side Javascript with Node.js by NIIT',
            description: 'Web Development & SEO | Internship',
            date: 'April 2023',
            details: 'Built server-side applications using Node.js and optimized websites for search engines.'
        },
        {
            type: 'certification',
            title: 'Cloud Computing 12 Weeks Course by NPTEL',
            description: 'Completed Course',
            date: 'October 2024',
            details: 'Studied cloud computing concepts, including virtualization and deployment models.'
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
                const html = `
                    <div class="container ${side}" data-type="${item.type}">
                        <div class="content">
                            <div class="tag">
                                <h2>${item.title}</h2>
                            </div>
                            <div class="desc">
                                <h3>${item.description}</h3>
                                <p>${item.date}</p>
                                <div class="details hidden">${item.details}</div>
                                <button class="toggle-details">Show Details</button>
                            </div>
                        </div>
                    </div>
                `;
                timeline.append(html);
            }
        });

        // Re-apply ScrollReveal animations after rendering
        ScrollReveal().reveal('.experience .timeline .container', { interval: 400 });
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
