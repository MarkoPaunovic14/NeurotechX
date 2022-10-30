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

const modelUser = mongoose.model('Users', schema);

async function pullUser(id) {
    const user = await modelUser.findOne({id: id}).exec();
    return user;
}

async function changeData(id, x, y){
    
    const doc = await modelUser.findOne({id:id}).exec();
    doc.location = {x : Number(x), y : Number(y)};
    // doc.location.y = Number(y);
    doc.save();
    return doc;    
}

async function changeNotify(id){
    const doc = await modelUser.findOne({id: id}).exec();
    doc.notify = 0;
    doc.save();
    return doc;
}

async function setSleepy(id){
    const doc = await modelUser.findOne({id: id}).exec();
    doc.sleepy = 1;
    doc.save();
    notifyAll(doc.id);
    return doc;
}

async function resetSleepy(id){
    const doc = await modelUser.findOne({id: id}).exec();
    doc.sleepy = 0;
    doc.save();
    return doc;
}


async function notifyAll(id){
    const doc = await modelUser.findOne({id: id}).exec();
    const allUsers = await modelUser.find({}).exec();
    for(let i = 0; i < 4; i++){
        if(allUsers[i].sleepy == 1){
            allUsers[i].notify = 0;
            console.log("uslo");
            continue;
        }
        allUsers[i].notify = 1;
    }
    for(let i = 0; i < 4; i++){
        allUsers[i].save();
    }
}


module.exports = {
    pullUser,
    changeNotify,
    changeData,
    setSleepy,
    resetSleepy,
};
