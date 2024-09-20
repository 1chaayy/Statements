document.querySelectorAll('.dropdown .toggle').forEach(function(toggle) {
    toggle.addEventListener('click', function(e) {
        e.preventDefault();
        var nextUl = toggle.nextElementSibling;
        if (nextUl && nextUl.tagName === 'UL') {
            nextUl.classList.toggle('open');
            toggle.classList.toggle('open');
        }
    });
});