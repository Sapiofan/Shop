var checkList1 = document.getElementById('list1');
var priceRange = document.getElementById('price');
checkList1.getElementsByClassName('anchor')[0].onclick = function(evt) {
  if (checkList1.classList.contains('visible'))
    checkList1.classList.remove('visible');
  else
    checkList1.classList.add('visible');
}

priceRange.getElementsByClassName('anchor')[0].onclick = function(evt) {
  if (priceRange.classList.contains('visible'))
    priceRange.classList.remove('visible');
  else
    priceRange.classList.add('visible');
}

var checkList2 = document.getElementById('list2');
checkList2.getElementsByClassName('anchor')[0].onclick = function(evt) {
  if (checkList2.classList.contains('visible'))
    checkList2.classList.remove('visible');
  else
    checkList2.classList.add('visible');
}

var checkList3 = document.getElementById('list3');
checkList3.getElementsByClassName('anchor')[0].onclick = function(evt) {
  if (checkList3.classList.contains('visible'))
    checkList3.classList.remove('visible');
  else
    checkList3.classList.add('visible');
}

const rangeInput = document.querySelectorAll(".range-input input"),
priceInput = document.querySelectorAll(".price-input input"),
range = document.querySelector(".slider .progress");
let priceGap = 500;
priceInput.forEach(input =>{
    input.addEventListener("input", e =>{
        let minPrice = parseInt(priceInput[0].value),
        maxPrice = parseInt(priceInput[1].value);
        
        if((maxPrice - minPrice >= priceGap) && maxPrice <= rangeInput[1].max){
            if(e.target.className === "input-min"){
                rangeInput[0].value = minPrice;
                range.style.left = ((minPrice / rangeInput[0].max) * 100) + "%";
            }else{
                rangeInput[1].value = maxPrice;
                range.style.right = 100 - (maxPrice / rangeInput[1].max) * 100 + "%";
            }
        }
    });
});
rangeInput.forEach(input =>{
    input.addEventListener("input", e =>{
        let minVal = parseInt(rangeInput[0].value),
        maxVal = parseInt(rangeInput[1].value);
        if((maxVal - minVal) < priceGap){
            if(e.target.className === "range-min"){
                rangeInput[0].value = maxVal - priceGap
            }else{
                rangeInput[1].value = minVal + priceGap;
            }
        }else{
            priceInput[0].value = minVal;
            priceInput[1].value = maxVal;
            range.style.left = ((minVal / rangeInput[0].max) * 100) + "%";
            range.style.right = 100 - (maxVal / rangeInput[1].max) * 100 + "%";
        }
    });
});
