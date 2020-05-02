var mongoose = require('mongoose');
var mongoosePaginate = require('mongoose-paginate');


var HeroSchema = new mongoose.Schema({
    id: Number,
    name: String
});

HeroSchema.plugin(mongoosePaginate);
const Hero = mongoose.model('Hero', HeroSchema);

module.exports = Hero;