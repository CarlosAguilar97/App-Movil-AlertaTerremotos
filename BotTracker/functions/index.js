const express = require("express");
const app = express();
const {Telegraf} = require("telegraf");
const bot = new Telegraf("5702400434:AAFdck8QgdtuFnkaQOPU8j67MqXpUn8T_zU");
const admin = require("firebase-admin");
const credenciales = require("./key.json");
admin.initializeApp({credential: admin.credential.cert(credenciales)});
app.use(express.json());
app.use(express.urlencoded({extended: true}));
const db = admin.firestore();
let Es;
let Id;
let Ida;
const botRef = db.collection("Bot");
botRef
  .get()
  .then((results) => {
    const data = results.docs.map((doc) => ({
      id: doc.id,
      ...doc.data(),
    }));
    dataStore = [];
            //console.log(result);
            data.forEach(item => {
                dataStore.push({
                  id: item.id,
                  ID: item.ID,
                  Estado: item.Estado,
                  Idoc: item.Idoc,
                })
            });
    console.log("Toda data en la colección 'Bots' ", data);
    dataStore.forEach(item => {
    Es = item.Estado;
    if (Es == true){
      Id = item.Idoc
      console.log('Id recuperad '+ Id); 
      let nombre;
      let celular;
      let ciudad;
      let idp;
      let Idu;
      const PersonRef = db.collection("Persona").doc(Id);
      PersonRef.get().then((doc) => {
      if (!doc.exists) return;-
      console.log("Document data:", doc.data());
      let datos = []
      const obj = doc.data();
      nombre = obj.Nombre
      ciudad = obj.Ciudad
      celular = obj.Celular
      idp = obj.ID 
      Ida = obj.Ida 
      Idu = obj.Idu         
        bot.command('ubi', ctx => { 
          ctx.reply(
          'Datos de la Persona: \n'+
          'Nombre: '+nombre+'\n'+
          'Celular: '+celular+'\n'+
          'Ciudad: '+ciudad+'\n'+
          'ID: '+idp)});
      let nombreA;
      let celularA;
      let correoA;
      let parentesco;
      const ApoderadoRef = db.collection("Apoderado").doc(Ida);
      ApoderadoRef.get().then((doc) => {
      if (!doc.exists) return;-
      console.log("Document data:", doc.data());
      const obj2 = doc.data();
      nombreA = obj2.Nombre
      correoA = obj2.Correo
      celularA = obj2.Celular
      parentesco = obj2.parentesco
      idp = obj.ID           
        bot.command('apo', ctx => { 
          ctx.reply(
          'Datos de la Apoderado: \n'+
          'Nombre: '+nombreA+'\n'+
          'Celular: '+celularA+'\n'+
          'Correo: '+correoA+'\n'+
          'ID: '+idp)});   
          let Longitud;
      let Latitud;
      const UbicacionRef = db.collection("Ubicacion").doc(Idu);
      UbicacionRef.get().then((doc) => {
      if (!doc.exists) return;-
      console.log("Document data:", doc.data());
      const obj3 = doc.data();
      Longitud = obj3.Longitud
      Latitud = obj3.Latitiud      
        bot.command('map', ctx => { 
          ctx.reply(
          'Datos de la Ubicacion: \n'+
          'Longitud: '+Longitud+'\n'+
          'Latitud: '+Latitud)});
      });
      
      });
      
      bot.start((ctx) => ctx.reply("Bienvenido Sistema de Alerta de Sismos!\n"+
      "/ubi = Muestra datos de la persona \n"+
      "/apo = Muestra datos de apoderado \n"+
      "/map = Muestra ubicacion de la persona"));
      bot.launch();
    
    }); 
  }
});
      
}); 


const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is runnin on PORT ${PORT}.`);

});
