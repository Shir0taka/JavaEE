console.log("Work");

function formatDate(date) {
    const options = {
        month: 'long',
        day: 'numeric',
        year: 'numeric'
    };


    const parsedDate = new Date(date);

    if (isNaN(parsedDate.getTime())) {
        console.error("Invalid date format");
        return date;
    }

    return parsedDate.toLocaleDateString('en-US', options);
}

const releaseDateColumns = document.getElementsByClassName('releaseDateColumn');
console.log(releaseDateColumns)

if (releaseDateColumns.length > 0) {
    for (let i = 0; i < releaseDateColumns.length; i++) {
        const dateText = releaseDateColumns[i].textContent.trim();
        console.log(dateText)
        const formattedDate = formatDate(dateText);
        console.log(formattedDate)
        releaseDateColumns[i].textContent = formattedDate;
        console.log(formattedDate);
    }
} else {
    console.error("Element with class 'releaseDateColumn' not found");
}
