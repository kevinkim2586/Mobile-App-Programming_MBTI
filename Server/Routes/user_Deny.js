
module.exports = function(app,db){
    var express = require('express');
    var router = express.Router();
   
    router.post('/',function(req,res,next){
        userManager = require('../Global/global').userManager;
        userManager.wantDeny(req,res,db);
    });
    return router;
};