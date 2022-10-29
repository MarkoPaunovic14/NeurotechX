const temperature = [
    { grad: "Beograd", temperatura: 21 },
    { grad: "Novi Sad", temperatura: 18 },
    { grad: "Subotica", temperatura: 17 },
    { grad: "Nis", temperatura: 40 },
];

    document.body.style.display = 'flex';
    document.body.style.gap = '50px';
    document.body.style.textAlign = 'center';
    
for(const temp of temperature){
    const div = document.createElement('div');
    div.style.height = "450px";
    div.style.width = "80px";
    const divIn = document.createElement('div');
    divIn.style.width = '50px';
    divIn.style.margin ='auto'
    divIn.style.backgroundColor = vratiBoju(temp.temperatura);
    divIn.style.height = temp.temperatura * 10 + 'px';
    divIn.addEventListener('click', function(){
        temp.temperatura += Math.random() * 4 - 2;
        divIn.style.height = temp.temperatura * 10 + 'px';
        divIn.style.marginTop = 450 - temp.temperatura * 10 + 'px';
        divIn.style.backgroundColor = vratiBoju(temp.temperatura);
    });
    divIn.style.marginTop = 450 - temp.temperatura * 10 + 'px';
    div.appendChild(divIn);
    const p = document.createElement('p');
    const pText = document.createTextNode(temp.grad);
    p.appendChild(pText);
    div.appendChild(p);
    document.body.appendChild(div);
}


function vratiBoju(temp){
    if(temp >= 0 && temp <10){
        return 'blue';
    }
    else if(temp >= 10 && temp < 20){
        return 'yellow';
    }
    else if(temp >= 20 && temp < 30){
        return 'orange';
    }
    else{
        return 'red';
    }
}