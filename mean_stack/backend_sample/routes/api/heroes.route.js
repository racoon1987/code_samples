var express = require('express')
var router = express.Router()

var HeroService = require('../../services/hero.service');
// var ToDoController = require('../../controllers/todos.controller');


// Map each API to the Controller FUnctions

router.get('/heroes', async function (req, res, next) {

    var page = req.query.page ? req.query.page : 1
    var limit = req.query.limit ? req.query.limit : 10;

    try {

        var heroes = await HeroService.getHeros({}, page, limit)

        return res.status(200).json({ status: 200, data: heroes, message: "Succesfully Recieved" });

    } catch (e) {
        return res.status(400).json({ status: 400, message: e.message });
    }
});

router.get('/heroes/:id', async function (req, res, next) {

    var page = req.query.page ? req.query.page : 1
    var limit = req.query.limit ? req.query.limit : 10;
    var id = req.params.id;

    try {

        var heroes = await HeroService.getHeros({id: id}, page, limit)

        return res.status(200).json({ status: 200, data: heroes, message: "Succesfully Recieved" });

    } catch (e) {
        return res.status(400).json({ status: 400, message: e.message });
    }
});


router.post('/heroes', async function(req, res, next){

    var hero = {
        id: req.body.id,
        name: req.body.name,
    }

    try{
        var createdHero = await HeroService.createHero(hero)
        return res.status(201).json({status: 201, data: createdHero, message: "Succesfully Created"})
    }catch(e){
        return res.status(400).json({status: 400, message: " Creation was Unsuccesfull"})
    }
});

// router.post('/', ToDoController.createTodo)

router.put('/heroes/:id', async function(req, res, next){

    // Id is necessary for the update

    if(!req.params.id){
        return res.status(400).json({status: 400., message: "Id must be present"})
    }

    var id = req.params.id;

    console.log(req.body)

    var hero = {
        id,
        name: req.body.name ? req.body.name : null,
    }

    try{
        var updatedHero = await HeroService.updateHero(hero);
        return res.status(200).json({status: 200, data: updatedHero, message: "Succesfully Updated Tod"})
    }catch(e){
        return res.status(400).json({status: 400., message: e.message})
    }
})

// router.delete('/:id',ToDoController.removeTodo)


// Export the Router

module.exports = router;