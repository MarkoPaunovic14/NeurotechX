const model = require('../models/users');

async function pullUser(req, res, next) {
    try{
    const id = req.query.id;
    const user = await model.pullUser(id);
    //uradi nesto
    console.log(JSON.stringify(user));
    // res.render('1.ejs', {user});
    res.send(JSON.stringify(user));
    }catch(err){
        next(err);
    }
}

async function changeData(req, res, next) {
    try{
        const id = req.query.id;
        const x = req.query.x;
        const y = req.query.y;
        const korinsik = await model.changeData(location, notify, sleepy);
        //izmena podataka
    }
    catch(err){
        next(err);
    }
}

async function checkNotify(req, res, next) {
    try{
        
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
