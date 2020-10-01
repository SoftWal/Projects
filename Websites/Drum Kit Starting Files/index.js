var numberButton = document.querySelectorAll(".drum").length;
var audio;

for (let index = 0; index < numberButton; index++) {
    document.querySelectorAll("button")[index].addEventListener("click", sound);
   
}
document.addEventListener("keypress", sound);



//This is the function that makes a sound depending if the person clicks the right buttons
function sound(){
    if(this === document.querySelector(".w")  || event.key === "w"){
        audio = new Audio("sounds/tom-1.mp3");
        audio.play();
        animation("w");
    }
    if(this === document.querySelector(".s") || event.key === "s"){
        audio = new Audio("sounds/tom-2.mp3");
        audio.play();
        animation("s");
    }
    if(this === document.querySelector(".a") || event.key === "a"){
        audio = new Audio("sounds/tom-3.mp3");
        audio.play();
        animation("a");
    }
    if(this === document.querySelector(".d") || event.key === "d"){
        audio = new Audio("sounds/tom-4.mp3");
        audio.play();
        animation("d");
    }
    if(this === document.querySelector(".j") || event.key === "j"){
        audio = new Audio("sounds/snare.mp3");
        audio.play();
        animation("j");
    }
    if(this === document.querySelector(".k") || event.key === "k"){
        audio = new Audio('sounds/crash.mp3');
        audio.play();
        animation("k");
        
    }
    if(this === document.querySelector(".l") || event.key === "l"){
        audio = new Audio("sounds/kick-bass.mp3");
        audio.play();
        animation("l");
    }
}
function animation(currentKey){
    var anim = document.querySelector("." + currentKey);

    anim.classList.add("pressed");
    setTimeout(function(){
        anim.classList.remove("pressed");
    }, 100);
}



