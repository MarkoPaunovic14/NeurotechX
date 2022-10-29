const model = require('../models/meteo');

async function dohvatiStatistike(req, res, next) {
    try{
    const stats = await model.dohvatiStatistike();
    res.render('statistike.ejs', {stats});
    }catch(err){
        next(err);
    }
}

async function dohvatiDetalje(req, res, next) {
    try{
        const grad = req.query.grad;
        let sort = req.query.sort;
        const stats = await model.dohvatiPodatke(grad,sort);
        res.render('detalji.ejs', {stats});
    }
    catch(err){
        next(err);
    }
}

module.exports = {
    dohvatiStatistike,
    dohvatiDetalje,
};
