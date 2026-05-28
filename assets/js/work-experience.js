$(document).ready(function () {
    $('#menu').click(function () {
        $(this).toggleClass('fa-times');
        $('.navbar').toggleClass('nav-toggle');
    });

    $(window).on('scroll load', function () {
        $('#menu').removeClass('fa-times');
        $('.navbar').removeClass('nav-toggle');
        $('#scroll-top').toggleClass('active', window.scrollY > 60);

        const scrollable = document.documentElement.scrollHeight - window.innerHeight;
        const progress = scrollable > 0 ? (window.scrollY / scrollable) * 100 : 0;
        $('#scroll-progress').css('width', `${progress}%`);
    });

    if (typeof ScrollReveal !== 'undefined') {
        ScrollReveal().reveal('.work-card', {
            origin: 'bottom',
            distance: '40px',
            duration: 800,
            delay: 200,
            interval: 200,
            easing: 'ease-out',
            reset: false
        });
        ScrollReveal().reveal('.work-experience .heading, .work-experience .qoute', {
            origin: 'top',
            distance: '30px',
            duration: 700,
            delay: 100
        });
    }
});
