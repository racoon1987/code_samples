// Gettign the Newly created Mongoose Model we just created 

var Hero = require('../models/hero.model');

// Saving the context of this module inside the _the variable
_this = this

exports.getHeros = async function(query, page, limit){
   
    try {
        var heroes = await Hero.find(query).select({"name": 1, "id": 1, "_id": 0});
        
        return heroes;

    } catch (e) {
        throw Error('Error while Paginating ')
    }
}

exports.createHero = async function(hero){

    var newHero = new Hero({
        id: hero.id,
        name: hero.name
    })

    try{
        var savedHero = await newHero.save()

        return savedHero;
    }catch(e){
        throw Error("Error while Creating ")
    }
}

exports.updateHero = async function(hero){
    var id = hero.id

    try{
        var oldHero = await Hero.findOneAndUpdate({id: id}, {name: hero.name});
        return oldHero;
    }catch(e){
        console.log(e);
        throw Error("And Error occured while updating the Hero");
    }
}

exports.deleteHero = async function(id){
    try{
        var deleted = await Hero.remove({_id: id})
        if(deleted.result.n === 0){
            throw Error("Hero Could not be deleted")
        }
        return deleted
    }catch(e){
        throw Error("Error Occured while Deleting the Hero")
    }
}