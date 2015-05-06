/*
 * Author: Brandy Lee Camacho
 */

// Using Hapi.js Framework --> hapijs.com
var Hapi = require('hapi');
var Good = require('good');
var mysql = require("mysql");

// MYSQL connection information
var MYSQLconnection = function(){ 
    return mysql.createConnection({
        host     : 'localhost',
        user     : 'root',
        password : '',
        database : 'test'
    });
};

// Method to create todays date
Date.prototype.today = function () {
    return ((this.getDate() < 10)?"0":"")
        + this.getDate()
        +"/"
        +(((this.getMonth()+1) < 10)?"0":"")
        + (this.getMonth()+1)
        +"/"
        + this.getFullYear();
}

// Method to create current time
Date.prototype.timeNow = function () {
    return ((this.getHours() < 10)?"0":"")
        + this.getHours()
        +":"
        + ((this.getMinutes() < 10)?"0":"")
        + this.getMinutes()
        +":"
        + ((this.getSeconds() < 10)?"0":"") + this.getSeconds();
}

//starting Hapi server with port 3000
var server = new Hapi.Server();
server.connection({port:3000});

//creating routes for API
server.route({
    method: 'POST',
    path: '/log',
        config: {
        handler: function(request, reply) {
            console.log('running API route handler');
            connection = new MYSQLconnection();
            console.log('connecting to MYSQL database');
            connection.connect();
            console.log('running mysql query to log user timestamp for '+request.payload.username);
            connection.query('INSERT INTO log (username, time_stamp) values ('+'"'+request.payload.username+'"'+','+'"Login: ' + new Date().today() + ' @ ' + new Date().timeNow()+'"'+')',
                function (err, result)
                {
                    if (err) throw err;
                    // perform addition functions here
                    console.log(connection.query);
                });
            connection.end();
            reply('Database submission was successful');
        }
    }
});

server.route({
    method:'GET',
    path:'/{user?}',
    handler: function (request, reply) {
        if(request.params.user){
            var message = "Hello, " + encodeURIComponent(request.params.user);
        } else {
            var message = "Hello there! Try adding your name to the end of the url, i.e. http://localhost/myName";
        }
        console.log(message);
        reply(message);
    }
});

/* log for the server */
server.register({
    register: Good,
    options: {
        reporters: [{
            reporter: require('good-console'),
            events: {
                response: '*',
                log: '*'
            }
        }]
    }
}, function (err) {
    if (err) {
        throw err; // something bad happened loading the plugin
    }

    server.start(function () {
        server.log("Server is running at:", server.info.uri);
    });
});