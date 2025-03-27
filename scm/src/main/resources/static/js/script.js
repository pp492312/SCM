console.log("script loaded");
// Theme light and dark
// change theme work
let currentTheme=getTheme();
// initial--->
document.addEventListener("DOMContentLoaded",() => {
    changeTheme();
});
 
function changeTheme(){
 // set to web page
 changePageTheme(currentTheme,"");
 //document.querySelector("html").classList.add(currentTheme);

 // set the listener to change the theme button
 const changrThemeButton=document.querySelector("#theme_change_button")
// change the text of the button 
// changrThemeButton.querySelector("span").textContent=
// currentTheme == "light"?"Dark":"Light";

changrThemeButton.addEventListener("click",(event) => {
    let oldTheme=currentTheme;
    
    console.log("change theme button clicked");
    
    if(currentTheme==="dark"){
        currentTheme="light";
    }
    else{
        currentTheme="dark";
    }
    changePageTheme(currentTheme,oldTheme);
 });
}

// set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}

// get theme from localstorage
function getTheme(){
    let theme=localStorage.getItem("theme");
    return theme ? theme : "light";
}

// change current page theme
function changePageTheme(theme,oldTheme){
    // localStorage mein update karenge
    setTheme(currentTheme);

    // remove the current theme
    if(oldTheme){
        document.querySelector("html").classList.remove(oldTheme);
    }
    // set the current theme
    document.querySelector("html").classList.add(theme);

    // change the text of the button 
    document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent=currentTheme == "light"?"Dark":"Light";
}