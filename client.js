fetch('http://localhost:8080/api/v1/social')
    .then(response => {
        if (!response.ok) {
            throw new Error('Request failed with status code ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
    })
    .catch(error => {
        console.error(error.message);
    });
