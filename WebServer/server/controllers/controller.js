const model = require('../models/users');

async function pullUser(req, res, next) {
    try{
    const id = req.query.id;
    const user = await model.dohvatiKorisnika(id);
    //uradi nesto
    console.log(JSON.stringify(user));
    res.render('1.ejs', {user});
    // res.send(JSON.stringify(user));
    }catch(err){
        next(err);
    }
}

async function changeData(req, res, next) {
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

async function checkNotify(req, res, next) {
    try{

        //izmena podataka
    }
    catch(err){
        next(err);
    }
}


module.exports = {
    pullUser,
    changeData,
    checkNotify,
};