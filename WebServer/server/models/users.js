const mongoose = require('mongoose');

const schema = new mongoose.Schema({
_id : mongoose.Schema.Types.ObjectId,
id:{
    type: Number,
    required: true
},
name:{
    type: String,
    required: true
},
location:{
    type: Object,
    required: true
},
notify:{
    type: Number,
    required: true
},
sleepy:{
    type: Number,
    required: true
}
}, {collection: "Users"});

const modelStat = mongoose.model('Users', schema);

async function dohvatiKorisnika() {

}

async function izmeniPodatke(location, notify, sleepy) {

}
module.exports = {
    dohvatiKorisnika,
    izmeniPodatke,
};
