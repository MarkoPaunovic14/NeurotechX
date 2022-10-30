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
        console.log("Changed user ------> " + user)
        res.render('1.ejs', {user});
        //izmena podataka
    }
    catch(err){
        next(err);
    }
}

async function changeNotify(req, res, next) {
    try{
        
    }
    catch(err){
        next(err);
    }
}


module.exports = {
    pullUser,
    changeData,
    changeNotify,
};
