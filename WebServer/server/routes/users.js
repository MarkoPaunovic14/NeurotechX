const express = require("express");
const controller = require("../controllers/controller.js");
const { pullUser } = require("../models/users.js");

const router = express.Router();

router.get('/locationChange', controller.changeData);
router.get('/changeNotify', controller.changeNotify);
router.get('/pullUser', controller.pullUser);
router.get('/setSleepy', controller.setSleepy);
router.get('/resetSleepy', controller.resetSleepy);

module.exports = router;
