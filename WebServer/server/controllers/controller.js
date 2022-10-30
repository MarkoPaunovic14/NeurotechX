const model = require('../models/users');

async function pullUser(req, res, next) {
    try{
    const id = req.query.id;
    const user = await model.pullUser(id);
    //uradi nesto
    console.log(JSON.stringify(user));
    res.render('1.ejs', {user});
    //res.send(JSON.stringify(user));
    }catch(err){
        next(err);
    }
}

async function changeData(req, res, next) {
    try{
        const id = req.query.id;
        const x = req.query.x;
        const y = req.query.y;
        const user = await model.changeData(id, x, y);
        console.log("Changed user location ------> " + user.id + " " + user.location);
        res.render('1.ejs', {user});
        //izmena podataka
    }
    catch(err){
        next(err);
    }
}

async function changeNotify(req, res, next) {
    try{
        const id = req.query;
        const user = await model.changeNotify(id);
        console.log("Changed notify back to 0 for user --> " + user.id);
        res.render('1.ejs', {user});
    }
    catch(err){
        next(err);
    }
}

async function setSleepy(req, res, next){
    try {
     const id = req.query.id;
     const user = await model.setSleepy(id);
     console.log("Change sleepy to 1 for user ---> " + user.id);
     res.render('1.ejs', {user});   
    } catch (err) {
        next(err);
    }
}

async function resetSleepy(req, res, next){
    try {
        const id = req.query.id;
        const user = await model.resetSleepy(id);
        console.log("Change sleepy to 0 for user ---> " + user.id);
        res.render('1.ejs', {user});   
       } catch (err) {
           next(err);
       }
}

module.exports = {
    pullUser,
    changeData,
    changeNotify,
    setSleepy,
    resetSleepy,
};
