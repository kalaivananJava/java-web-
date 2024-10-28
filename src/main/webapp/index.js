let uri="http://localhost:8080/customer";//annotation name used in servlet page 
let customers=[];
let updateIndex=0;

function getItems(){
    console.log("getItems");
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            _displayItems(JSON.parse(xhttp.responseText));
        }
    };
 xhttp.open("GET",uri,true);
 xhttp.send();
}
function _displayCount(itemCount) {
    const name = itemCount === 1 ? "customer" : "customers";
  
    document.getElementById("counter").innerText = `${itemCount} ${name}`;
  }


  function _displayItems(data){
    let tBody=document.getElementById("customers");
    tBody.innerHTML="";
    _displayCount(data.length);
    let button=document.createElement("button");
 
    data.forEach((item) => {
    let editButton=button.cloneNode(false);   
    editButton.innerText="Edit";
    editButton.setAttribute("onclick",`editItem(${item.id})`);

    let deleteButton =button.cloneNode(false);
    deleteButton.innerHTML="Delete";
    deleteButton.setAttribute("onclick",`deleteItem(${item.id})`);
   
    let tr=tBody.insertRow();
    // let td1=tr.insertCell(0);
    // let cusBookingDate=document.createTextNode(item.booking_date);
    // td1.appendChild(cusBookingDate);

    // "booking_date": "Aug 22, 2024",
    // "bus_no": 8,
    // "bus_name": "Bus B",
    // "avail_seats": 5,
    // "customer_name": "Alex",
    // "id": 2
    let td2=tr.insertCell(0);
    let cusBus_no=document.createTextNode(item.bus_no);
    td2.appendChild(cusBus_no);

    let td3=tr.insertCell(1);
    let cusBus_name=document.createTextNode(item.bus_name);
    td3.appendChild(cusBus_name);

    let td4=tr.insertCell(2);
    let cusAvailSeats = document.createTextNode(item.avail_seats);
    td4.appendChild(cusAvailSeats);

    let td5=tr.insertCell(3);
    let cus_name=document.createTextNode(item.customer_name);
    td5.appendChild(cus_name);

    let td6=tr.insertCell(4);
    let cus_id=document.createTextNode(item.id);
    td6.appendChild(cus_id);

    let td7=tr.insertCell(5);
    td7.appendChild(editButton);

    let td8=tr.insertCell(6);
    td8.appendChild(deleteButton);



});
customers=data;

  }

//   "booking_date": "Aug 22, 2024",
//   "bus_no": 8,
//   "bus_name": "Bus B",
//   "avail_seats": 5,
//   "customer_name": "Alex",
//   "id": 2

  function editItem(id1) {
    document.getElementById("myBtn").innerHTML = "Update";
    const item = customers.find(x=> x.id === id1);
    console.log(item);
    document.getElementById("busno").value=item.bus_no;
    document.getElementById("busname").value=item.bus_name;
    document.getElementById("availableseats").value=item.avail_seats;
    document.getElementById("customername").value=item.customer_name;
    
    updateIndex = id1;
  }
//   "booking_date": "Aug 22, 2024",
//   "bus_no": 8,
//   "bus_name": "Bus B",
//   "avail_seats": 5,
//   "customer_name": "Alex",
//   "id": 2
 

function addItem(){
   console.log("Add item called");

   let newCustomer={
  
    bus_no:document.getElementById("busno").value,
    bus_name:document.getElementById("busname").value,
    avail_seats:document.getElementById("availableseats").value,
    customer_name:document.getElementById("customername").value

   }
   console.log(newCustomer);

   var xhttp = new XMLHttpRequest();
   xhttp.open("POST",uri,true);
   xhttp.setRequestHeader("Content-type","application/json");
   xhttp.send(JSON.stringify(newCustomer));
   xhttp.onreadystatechange=function(){
    if(this.readyState == 4 && this.status == 200)
        getItems();
   }
   document.getElementById("busno").value=""
   
   document.getElementById("busname").value=""
   
   document.getElementById("availableseats").value=""
   document.getElementById("customername").value=""

}

function saveORupdateItem(){
    console.log("save or update");
    document.getElementById("myBtn").innerHTML== "save" ? addItem() : updateItem();
}
//   "booking_date": "Aug 22, 2024",
//   "bus_no": 8,
//   "bus_name": "Bus B",
//   "avail_seats": 5,
//   "customer_name": "Alex",

function deleteItem(ids){
console.log("do delete...")
 let item={
   
//  bus_no:document.getElementById("busno").value.trim(),
    
//  bus_name:document.getElementById("busname").value.trim(),
   
//  avail_seats:document.getElementById("availableseats").value.trim(),

//  customer_name:document.getElementById("customername").value.trim(),
    id:ids
 }
var xhttp = new XMLHttpRequest();
xhttp.open("DELETE",uri,true);
xhttp.setRequestHeader("Content-type","application/json");
xhttp.send(JSON.stringify(item));
xhttp.onreadystatechange=function(){
    if (this.readyState == 4 && this.status == 200) {
        getItems();
    }
}
}


function updateItem(){
console.log("UPdate");
    const item={
        id:updateIndex,
        bus_no:document.getElementById("busno").value.trim(),
    bus_name:document.getElementById("busname").value.trim(),
    avail_seats:document.getElementById("availableseats").value.trim(),
    customer_name:document.getElementById("customername").value.trim()

    };

    var xhttp=new XMLHttpRequest();
    xhttp.open("PUT",uri,true);
    xhttp.setRequestHeader("Content-type","application/json");
    xhttp.send(JSON.stringify(item));

    document.getElementById("myBtn").innerHTML="save";
    document.getElementById("busno").value="";
    document.getElementById("busname").value="";
    document.getElementById("availableseats").value="";
    document.getElementById("customername").value="";
    updateIndex=0;
    getItems();

}
