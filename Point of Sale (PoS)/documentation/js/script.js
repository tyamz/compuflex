$(document).ready(function () {
    $('.menu .menu-list li a').click(function(e) {

        $('.menu .menu-list li a').removeClass('is-active');

        var $this = $(this);
        if (!$this.hasClass('is-active')) {
            $this.addClass('is-active');
        }
        //e.preventDefault();
    });
});
