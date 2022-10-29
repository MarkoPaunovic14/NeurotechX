const express = require("express");
const controller = require("../controllers/controller.js");
const { pullUser } = require("../models/users.js");

const router = express.Router();

router.get('/locationChange', controller.changeData);
router.get('/checkNotify', controller.checkNotify);
router.get('/pullUser', controller.pullUser)

module.exports = router;
