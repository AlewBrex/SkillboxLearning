$(function(){

    const appendThing = function(data){
        var thingCode = '<a href="#" class="thing-link" data-id="' +
            data.id + '">' + data.name + '</a>' + '<br>';
        $('#thing-list')
            .append('<div>' + thingCode + '</div>');
    };

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
                var code = '<span>Описание дела:' + response.description + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Дело не найдено!');
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
            url: '/thing-list/',
            data: data,
            success: function(response)
            {
                $('#thing-form').css('display', 'none');
                var thing = {};
                thing.id = response;
                var dataArray = $('#thing-form form').serializeArray();
                for(i in dataArray) {
                    thing[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendThing(thing);
            }
        });
        return false;
    });
});