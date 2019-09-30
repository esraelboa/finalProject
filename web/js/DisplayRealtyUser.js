 $(document).ready(function () {
     
      function CreateTableFromJSON(myRealty) {
    //console.log(myRealty);
       var col = [];
        for (var i = 0; i < myRealty.length; i++) {
            for (var key in myRealty[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }

     
        var table = document.createElement("table");

     

        var tr = table.insertRow(-1);                   

        for (var i = 0; i < col.length; i++) {
        
            var th = document.createElement("th");      
            th.innerHTML = col[i];
            tr.appendChild(th);
        }

      
        for (var i = 0; i < myRealty.length; i++) {

            tr = table.insertRow(-1);

            for (var j = 0; j < col.length; j++) {
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = myRealty[i][col[j]];
            }
        }

        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
    }
     
     
 $.ajax({
            url: " http://localhost:8080/finalPojest/getAllUserrRealtiesServlet",
            type: "GET",

            dataType: "json",
            success: function (result) {
CreateTableFromJSON(result['realties']);
                  
    
                  
            },
            error: function () {
                console.log("Error");
            }
        });

 });