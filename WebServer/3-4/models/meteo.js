const mongoose = require('mongoose');

const schema = new mongoose.Schema({
_id : mongoose.Schema.Types.ObjectId,
grad:{
    type: String,
    required: true
},
drzava:{
    type: String,
    required: true
},
temperatura:{
    type: Number,
    required: true
},
datum:{
    type: Date,
    required: true
}
}, {collection: "meteopodaci"});

const modelStat = mongoose.model('Meteo', schema);

async function dohvatiStatistike() {
    const gradovi = new Set();
    const statistike = await modelStat.find().exec();


    for(const stat of statistike){
        gradovi.add(stat.grad);
    }
    const stats = [];

    for(const grad of gradovi){
        let min = 1000;
        let max = -1000;
        let sum = 0;
        let avg;
        const tempGradAll = await modelStat.find({grad : grad}).exec();
        for(const tempGrad of tempGradAll) {
            if(tempGrad.temperatura < min){
                min = tempGrad.temperatura;
            }
            if(tempGrad.temperatura > max){
                max = tempGrad.temperatura;
            }
            sum += tempGrad.temperatura;
        }
        avg = sum/tempGradAll.length;
        stats.push({
            grad,
            drzava: tempGradAll[0].drzava, 
            min,
            avg,
            max
        });
    }
    return stats;
}

async function dohvatiPodatke(grad, sort) {
    const listaPodatakaZaGrad = await modelStat.find({grad: grad}).sort({datum : sort}).exec();
    console.log(sort);
    let stats = [];
    for(const stat of listaPodatakaZaGrad){
        stats.push(stat);
    }
    return stats;
}
module.exports = {
    dohvatiStatistike,
    dohvatiPodatke,
};
