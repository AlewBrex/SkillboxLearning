$(function(){

    const appendThing = function(data){
        var thingCode = '<a href="#" class="book-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#thing-list')
            .append('<div>' + thingCode + '</div>');
    };

    //Loading books on load page
//    $.get('/books/', function(response)
//    {
//        for(i in response) {
//            appendBook(response[i]);
//        }
//    });

    //Show adding book form
    $('#show-add-thing-form').click(function(){
        $('#thing-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#thing-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.thing-link', function(){
        var link = $(this);
        var thingId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/things/' + thingId,
            success: function(response)
            {
                var code = '<span>Год выпуска:' + response.year + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Книга не найдена!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-thing').click(function()
    {
        var data = $('#thing-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/things/',
            data: data,
            success: function(response)
            {
                $('#thing-form').css('display', 'none');
                var thing = {};
                book.id = response;
                var dataArray = $('#thing-form form').serializeArray();
                for(i in dataArray) {
                    book[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBook(book);
            }
        });
        return false;
    });
});