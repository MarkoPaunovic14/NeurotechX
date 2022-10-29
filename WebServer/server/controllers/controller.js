const model = require('../models/users');

async function dohvatiKorisnika(req, res, next) {
    try{
    const korinsik = await model.dohvatiKorisnika();

    //uradi nesto

    }catch(err){
        next(err);
    }
}

async function izmeniPodatke(req, res, next) {
    try{
        const location = req.query.location;
        let notify = req.query.notify;
        let sleepy = req.query.sleepy;
        const korinsik = await model.izmeniPodatke(location, notify, sleepy);
        //izmena podataka
    }
    catch(err){
        next(err);
    }
}

module.exports = {
    dohvatiKorisnika,
    izmeniPodatke,
};
