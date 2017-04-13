/**
 * 
 */
var counter = 1;
var limit = 3;
function addInput(divName){
     if (counter == limit)  {
          alert("You have reached the limit of adding " + counter + " inputs");
     }
     else {
          var newdiv = document.getElementById('divName');
//          newdiv.innerHTML = "<tr><td>Street Address:</td><td><form:input path=\"address.streetAddress\" size=\"30\" required=\"required\" /><font color=\"red\"><form:errors path=\"address.streetAddress\" /></font></td></tr>";
          document.getElementById(divName).appendChild(newdiv);
          counter++;
     }
}