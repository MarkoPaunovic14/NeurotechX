const forms = document.getElementsByTagName('form');
const form = forms[0];

form.addEventListener('submit',function(e){
    const radioBtns = document.querySelectorAll('input[type="radio"]');
    let pritisnut = false;
    for(const radio of radioBtns){
        if(radio.checked){
            pritisnut = true;
            break;
        }
    }
    if(!pritisnut){
        window.alert('izaberite sort');
        e.preventDefault();
    }
});