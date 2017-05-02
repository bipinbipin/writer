$('document').ready(function () {

    var userId = $('#userId').attr('value');
    var userUsername = $('#userUsername').attr('value');
    var userIdPlusNoteIndex = userId + "/0";
    var activeNote = {
        id: "",
        title: "",
        body: "",
        visible: false,
        created: "",
        tagList: [],
        user: {}
    };

    //UPDATE activeNote ON CHANGE
    $('input, textarea').change(function () {
        updateActiveNoteWithJspValues();
    });

    function updateActiveNoteWithJspValues() {
        activeNote.title = $('#noteTitle').val();
        activeNote.body = $('#writing-area').val();
        if ($('#isVisibleFalse').is(':checked')) {
            activeNote.visible = false;
            $('#noteIsVisible').val(false);
        } else {
            activeNote.visible = true;
            $('#noteIsVisible').val(true);
        }
        activeNote.created = $('#noteCreated').val();
        activeNote.tagList = $('#noteTagList').val().split(",");

        sessionStorage.setItem("sessionStorageActiveNote", JSON.stringify(activeNote));
    }

    function updateJspValuesWithSessionStorage() {
        var ssActiveNote = $.parseJSON(sessionStorage.getItem("sessionStorageActiveNote"));
        console.log(ssActiveNote);
        $('#noteTitle').val(ssActiveNote.title);
        $('#writing-area').val(ssActiveNote.body);
        $('#noteIsVisible').val(ssActiveNote.visible);
        $('#noteCreated').val(ssActiveNote.created);
        $('#noteTagList').val(ssActiveNote.tagList.toString());
    }

    //region CHECK IF USER IS LOGGED IN
    function loggedInStatus() {
        if (userId.length === 29) {
            $('#hamburger-menu').trigger("click");
            $('.nav-tabs a[href="#notes"]').tab('show');
            if (sessionStorage.length > 0) {
                updateJspValuesWithSessionStorage();
            }
            return true;
        } else {
            return false;
        }
    }

    //endregion

    //region ANIMATE SIDE-BAR OPEN / CLOSE
    var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';

    $('#hamburger-menu').hover(function () {
        $(this).addClass('animated pulse').one(animationEnd, function () {
            $(this).removeClass('animated pulse');
        });
    });

    $('#hamburger-menu').click(function () {
        $(this).toggleClass('is-active');
    });

    $('#hamburger-menu').click(function () {
        $('#note-panel-menu')
            .addClass('animated')
            .toggleClass('hidden fadeIn fadeOut')
            .one(animationEnd, function () {
                $(this).removeClass('animated');
            });

        $('#info-panel')
            .addClass('animated')
            .toggleClass('hidden fadeIn fadeOut')
            .one(animationEnd, function () {
                $(this).removeClass('animated');
            });
    });
    //endregion

    //region NEW-NOTE / CLEAR INPUT
    $('#new-note').click(function () {
        clearInputs();
    });

    function clearInputs() {
        $('#userIdPlusNoteIndex').val(userId + "/");

        $('#writing-area').val('');

        $('#noteTitle').val('');
        $('#noteCreated').val('');
        $('#noteTagList').val('');
        sessionStorage.clear();
    }

    //endregion

    //region GET NOTE LIST BY ID if(loggedIn === true)
    if (loggedInStatus()) {
        getAllNotes();
    }
    //endregion

    //region GET ALL NOTES filtered by server on user id
    function getAllNotes() {
        //clear existing note list
        $('.list-group').html('');

        //AJAX settings
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/api/note/all/" + userId,
            "method": "GET",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json",
            }
        }

        //get all notes, append them to note-list panel
        $.ajax(settings).done(function (noteArray) {
            $.each(noteArray, function (index, note) {
                userIdPlusNoteIndex = userId + "/" + index;
                $('.list-group').append(
                    '<a href="#" class="list-group-item individual-note"' +
                    'id="' + userIdPlusNoteIndex + '">' +
                    '<h4 class="list-group-item-heading">' + note.title + '</h4>' +
                    '<p>' + note.body.substr(0, 50) + '</p>' +
                    '</a>'
                );
            });
        });
    }

    //endregion

    //region GET INDIVIDUAL NOTE
    $('.list-group').on('click', '.individual-note', function () {
        userIdPlusNoteIndex = this.id;

        //AJAX settings
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/api/note/individual/" + this.id,
            "method": "GET",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json",
            }
        }

        //get individual note, API sets jsp attributes
        $.ajax(settings).done(function (note) {
            $('#noteTitle').val(note.title);
            $('#writing-area').val(note.body);
            $('#noteIsVisible').val(note.visible);
            $('#noteCreated').val(note.created);
            $('#noteTagList').val(note.tagList);

            $('.nav-tabs a[href="#home"]').tab('show');
        });

    });
    //endregion

    //region SAVE NOTE
    $('#save-button').click(function () {
        saveNote();
        $('.nav-tabs a[href="#notes"]').tab('show');
    });

    var saveNote = function () {
        if (loggedInStatus()) {
            updateActiveNoteWithJspValues();
            //AJAX settings
            var settings = {
                "async": true,
                "crossDomain": true,
                "url": "http://localhost:8080/api/note/save/" + userIdPlusNoteIndex,
                "method": "POST",
                "data": JSON.stringify(activeNote),
                "headers": {
                    "content-type": "application/json",
                    "accept": "application/json",
                }
            }

            //get individual note, API sets jsp attributes
            $.ajax(settings).done(function () {
                clearInputs();
                getAllNotes();
            });
        }
    }
    //endregion

    //region DELETE NOTE
    $('#delete-button').click(function () {
        //AJAX settings
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/api/note/delete/" + userIdPlusNoteIndex,
            "method": "POST",
            "data": JSON.stringify(activeNote),
            "headers": {
                "content-type": "application/json",
                "accept": "application/json",
            }
        }

        //get individual note, API sets jsp attributes
        $.ajax(settings).done(function () {
            clearInputs();
            getAllNotes();
        });
        $('.nav-tabs a[href="#notes"]').tab('show');
    });
    //endregion

    //region CREATE NEW USER
    $('#new-user-save').click(function () {

        //region FRONT-END VALIDATION
        if ($('#newUserUsername').val() == "") {
            $('#newUserUsername').attr("placeholder", "Please enter a username.")
                .parent().parent().addClass('has-warning');
        } else if ($('#newUserPassword').val() == "") {
            $('#newUserPassword').attr("placeholder", "Please enter a password.")
                .parent().parent().addClass('has-warning');
        } else if ($('#newUserPassword').val() != $('#newUserPasswordVal').val()) {
            $('#newUserPassword').val("").attr("placeholder", "Passwords don't match.")
                .parent().parent().addClass('has-warning');
            $('#newUserPasswordVal').val("").attr("placeholder", "Passwords don't match.")
                .parent().parent().addClass('has-warning');
        } else
        //endregion
        {
            var user = {
                firstName: $('#newUserFirstName').val(),
                lastName: $('#newUserLastName').val(),
                emailAddress: $('#newUserEmailAddress').val(),
                username: $('#newUserUsername').val(),
                password: $('#newUserPassword').val(),
                grantedAuthorities: []
            };

            console.log(user);

            var settings = {
                "async": true,
                "crossDomain": true,
                "url": "http://localhost:8080/api/user/",
                "method": "POST",
                "headers": {
                    "content-type": "application/json",
                    "cache-control": "no-cache"
                },
                "data": JSON.stringify(user)
            }

            $.ajax(settings).done(function (response) {
                //close modal
                $('#create-account-modal').modal('toggle');
                //populate username and password fields
                $('#inputUsername').val(user.username);
                $('#inputPassword').val(user.password);
                //submit username and password to login
                $('#loginButton').trigger('click');
            });
        }


    })
    //endregion

    $('#logout').click(function () {
        sessionStorage.clear();
        document.getElementById("logoutForm").submit();
    });

    //region Big Huge Thesaurus API
    $('#search-button').click(function () {
        //http://words.bighugelabs.com/api/{version}/{api key}/{word}/{format}
        var version = "2";
        //todo: make this a private variable
        var apiKey = "42d1acd705871a94027df91abc176426";
        var word = $('#search-input').val();

        var settings = {
            "async": true,
            "crossDomain": true,
            "dataType": "JSONP",
            "url": "http://words.bighugelabs.com/api/" + version + "/" + apiKey + "/" + word + "/json",
            "method": "GET",
            "headers": {
                "Access-Control-Allow-Origin": "*",
            }
        }

        $.ajax(settings).done(function (response) {
            $(".panel-default").removeClass("hidden");
            $(".panel-body").html("");

            var nounArr = response["noun"]["syn"];
            $.each(nounArr, function (i, word) {
                $('#noun-panel-body').append(word).append(", ");
            });
            var verbArr = response["verb"]["syn"];
            $.each(verbArr, function (i, word) {
                $('#verb-panel-body').append(word).append(", ");
            })
        });

    });
    //endregion

    $('#clear-search').click(function () {
        getAllNotes();
    });

    //region SEARCH TAGS
    $('#search-tags-button').click(function () {
        var keyword = $('#search-tags').val();

        //clear existing note list
        $('.list-group').html('');

        //AJAX settings
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/api/note/" + userId + "/" + keyword,
            "method": "GET",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json",
            }
        }

        //get all notes, append them to note-list panel
        $.ajax(settings).done(function (noteArray) {
            if (noteArray.length > 0) {
                $.each(noteArray, function (index, note) {
                    userIdPlusNoteIndex = userId + "/" + index;
                    $('.list-group').append(
                        '<a href="#" class="list-group-item individual-note"' +
                        'id="' + userIdPlusNoteIndex + '">' +
                        '<h4 class="list-group-item-heading">' + note.title + '</h4>' +
                        '<p>' + note.body.substr(0, 50) + '</p>' +
                        '</a>'
                    );
                });
            } else {
                $('.list-group').append(
                    '<p>No notes found.</p>'
                )
            }
        });
    });
    //endregion

});
