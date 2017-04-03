<%@include file="includes/head.jsp" %>
<%@include file="includes/nav-sidebar.jsp" %>

<%--START #writing-area-div --%>
<div class="col-lg-6" id="writing-area-div">
    <textarea name="writing-area" id="writing-area" placeholder="" autofocus>${noteBody}</textarea>
</div>
<%--END #writing-area-div --%>

<%--START #info-panel --%>
<div class="col-lg-3 hidden fadeOut" id="info-panel">
    <div class="col-lg-10 pull-right">
        <br>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon glyphicon glyphicon-search"></span>
                <input id="search-input" type="text" class="form-control" placeholder="Big Huge Thesaurus">
                        <span class="input-group-btn">
                        <button id="search-button" class="btn btn-default" type="button">Go</button>
                    </span>
            </div>
        </div>
        <div class="panel panel-default hidden">
            <div class="panel-heading">Noun</div>
            <div id="noun-panel-body" class="panel-body">
                <%--FILL IN NOUN SYN. DYNAMICALLY HERE--%>
            </div>
        </div>
        <div class="panel panel-default hidden">
            <div class="panel-heading">Verb</div>
            <div id="verb-panel-body" class="panel-body">
                <%--FILL IN VERB SYN. DYNAMICALLY HERE--%>
            </div>
        </div>
    </div>
</div>
<%--END #info-panel --%>
</div>
<%--END ROW--%>
</div>
<%--END CONTAINER-FLIUD--%>
</body>
</html>