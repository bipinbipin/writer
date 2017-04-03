<%--MODEL ATTRIBUTE OF USER FROM "/" REQUEST MAPPING--%>
<data class="hidden" id="userId" value="${userId}"></data>
<data class="hidden" id="userUsername" value="${userUsername}"></data>

<div class="container-fluid">
    <div class="row no-gutter">

        <%--START #note-panel --%>
        <div class="col-lg-3" id="note-panel">
            <%--KEEP EMPTY--%>

            <div class="col-lg-12" id="note-panel-wrapper">

                <%--START #note-panel-menu --%>
                <div class="col-lg-10 hidden fadeOut" id="note-panel-menu">

                    <%--START TABS--%>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#home" id="home-tab-button" data-toggle="tab" aria-expanded="true">Home</a>
                        </li>
                        <li class="">
                            <a href="#notes" id="notes-tab-button" data-toggle="tab" aria-expanded="false">Notes</a>
                        </li>
                    </ul>
                    <%--END TABS--%>

                    <%--START HOME TAB--%>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active" id="home">

                            <%--START NOTE DETAILS--%>
                            <div class="col-lg-12" id="note-details">
                                <form class="form-horizontal">
                                    <fieldset>
                                        <legend>Note Details</legend>
                                        <data class="hidden" id="userIdPlusNoteIndex"
                                              value="${userIdPlusNoteIndex}"></data>

                                        <div class="form-group">
                                            <label for="noteTitle" class="col-lg-3 control-label">Title</label>

                                            <div class="col-lg-9">
                                                <input type="text" class="form-control" id="noteTitle"
                                                       value="${noteTitle}"
                                                       placeholder="Title">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-lg-3 control-label" for="noteCreated">Created</label>

                                            <div class="col-lg-9">
                                                <input class="form-control" id="noteCreated" type="text"
                                                       placeholder="Date Created" disabled="" value="${noteCreated}">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="noteTagList" class="col-lg-3 control-label">Tags</label>

                                            <div class="col-lg-9">
                                                <textarea class="form-control" rows="3"
                                                          id="noteTagList">${noteTagList}</textarea>
                                                <span class="help-block">Separate tags with a comma. Tag search is case sensitive.</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Visibility</label>
                                            <data id="noteIsVisible" value="${noteIsVisible}"></data>

                                            <div class="col-lg-9">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="optionsRadios" id="isVisibleFalse"
                                                               value="false" checked="">
                                                        Private
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="optionsRadios" id="isVisibleTrue"
                                                               value="true">
                                                        Public
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-6 col-lg-offset-6">
                                    <button type="button" id="save-button" class="btn btn-block btn-default">Save</button>
                                    <button type="button" id="new-note" class="btn btn-block btn-default">New</button>
                                    <button type="button" id="delete-button" class="btn btn-block btn-default">Delete</button>
                                </div>
                            </div>
                        </div>
                        <%--END HOME TAB--%>

                        <%--START NOTES PANE--%>
                        <div class="tab-pane col-lg-12" id="notes">
                            <br>

                            <c:if test="${not empty userId}">
                                <%--todo: SEARCH TAGS--%>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon glyphicon glyphicon-search"></span>
                                        <input id="search-tags" type="text" class="form-control" placeholder="Search Notes">
                                        <span class="input-group-btn">
                                        <button id="search-tags-button" class="btn btn-default" type="button">Go</button>
                                        </span>
                                    </div>
                                </div>
                                <button type="button" id="clear-search" class="btn btn-block btn-default">Clear Search
                                </button>
                                <br>
                            </c:if>

                            <%--Insert saved notes dynamically here--%>
                            <div class="list-group">
                                <%--DYNAMICALLY FILLED NOTES ON LOGIN--%>
                            </div>
                            <c:if test="${not empty userId}">
                                <button type="button" id="logout" class="btn btn-block btn-default">Logout
                                </button>

                                <%--LOGOUT FORM--%>
                                <form action="/logout" method="post" id="logoutForm">
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                </form>
                                <%--END LOGOUT FORM--%>

                            </c:if>
                            <%--LOG IN OPTIONS APPEAR IF NOT LOGGED IN--%>
                            <c:if test="${empty userId}">
                                <div class="logIn col-lg-12">
                                    <p>Please log in to access your notebook.</p>

                                    <form id="loginForm" name="f" action="/login" method="POST" class="form-horizontal">
                                        <fieldset>
                                            <legend>Log In</legend>
                                            <div class="form-group">
                                                <div class="col-lg-12">
                                                    <input type="text" name="username" class="form-control"
                                                           id="inputUsername"
                                                           placeholder="Username">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12">
                                                    <input type="password" name="password" class="form-control"
                                                           id="inputPassword"
                                                           placeholder="Password">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12">
                                                    <input type="hidden"
                                                           name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <button name="submit" type="submit" id="loginButton" value="Login"
                                                            class="btn btn-default pull-right">Submit
                                                    </button>
                                                </div>
                                            </div>
                                                <%--SUCCESSFUL LOGOUT MESSAGE--%>
                                            <c:if test="${not empty msg}">
                                                <div class="msg">${msg}</div>
                                            </c:if>
                                                <%--END SUCCESSFUL LOGOUT MESSAGE--%>
                                        </fieldset>
                                    </form>
                                        <%--MKYONG ERROR MESSAGES--%>
                                    <c:if test="${not empty error}">
                                        <div class="error alert alert-dismissible alert-warning ">
                                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                                            <h4>Oops!</h4>

                                            <p>${error}</p>
                                        </div>
                                    </c:if>
                                        <%--END MKYONG ERROR MESSAGES--%>

                                    <button type="button" name="create-account" id="create-account"
                                            class="btn btn-default pull-right" data-toggle="modal"
                                            data-target="#create-account-modal">Create Account
                                    </button>

                                    <div class="modal" id="create-account-modal">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title">Create Account</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <form name="create" class="form-horizontal">
                                                        <fieldset>

                                                            <div class="form-group">
                                                                <label for="newUserFirstName"
                                                                       class="col-sm-2 control-label">First Name</label>

                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control"
                                                                           id="newUserFirstName"
                                                                           placeholder="First Name">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="newUserLastName"
                                                                       class="col-sm-2 control-label">Last Name</label>

                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control"
                                                                           id="newUserLastName"
                                                                           placeholder="Last Name">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="newUserEmailAddress"
                                                                       class="col-sm-2 control-label">Email</label>

                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control"
                                                                           id="newUserEmailAddress"
                                                                           placeholder="Email">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="newUserUsername"
                                                                       class="col-sm-2 control-label">Username</label>

                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control"
                                                                           id="newUserUsername"
                                                                           placeholder="Username (required)">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="newUserPassword"
                                                                       class="col-sm-2 control-label">Password</label>

                                                                <div class="col-sm-10">
                                                                    <input type="password" class="form-control"
                                                                           id="newUserPassword"
                                                                           placeholder="Password (required)">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="newUserPasswordVal"
                                                                       class="col-sm-2 control-label">Repeat
                                                                </label>

                                                                <div class="col-sm-10" id="passwordValidation">
                                                                    <input type="password" class="form-control"
                                                                           id="newUserPasswordVal"
                                                                           placeholder="Repeat Password (required)">
                                                                </div>
                                                            </div>

                                                            <div class="modal-footer" id="modal-footer">
                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal">Close
                                                                </button>
                                                                <button type="button" value="" id="new-user-save"
                                                                        class="btn btn-default">Save changes
                                                                </button>
                                                            </div>
                                                        </fieldset>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--END LOGIN OPTIONS--%>
                            </c:if>
                        </div>
                        <%--END NOTES PANE--%>
                        <%--END NOTE DETAILS PANE--%>
                    </div>
                    <%--END #note-panel-menu --%>
                </div>
                <%--END #note-panel-wrapper --%>
                <div class="col-lg-2" id="note-panel-button-div">
                    <button id="hamburger-menu" class="hamburger hamburger--spin" type="button">
                    <span class="hamburger-box">
                    <span class="hamburger-inner"></span>
                    </span>
                    </button>
                </div>
            </div>
        </div>
<%--END #note-panel --%>