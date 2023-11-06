function isNumberKey(evt) {
    let charCode = (evt.which) ? evt.which : evt.keyCode;
    if(charCode >= 48 && charCode <= 57 || charCode <= 31) {
        return true;
    }
    return false;
}

function searchRest(categoryId) {
    let t = document.getElementById("searchType");

    if (categoryId == null) {
        t.value = "Text";
    } else {
        t.value = "Category";
        document.getElementById("categoryId").value = categoryId;
    }

    document.getElementById("form").submit();
}