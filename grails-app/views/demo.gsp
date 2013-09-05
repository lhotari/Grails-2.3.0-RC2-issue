<!DOCTYPE HTML>
<html>
<head>
    <r:require modules="colorcycle"/>
    <style type="text/css">
    div {
        background-color: tomato;
        width: 400px;
        height: 20px;
        line-height: 20px;
        font-family: "Helvetica Neue Light", "Verdana";
        margin-bottom: 10px;
        padding: 10px;
    }
    </style>
    <r:layoutResources/>
</head>
<body>

<script>
    var successText = ' - success...';
</script>

<demo:main>
    <demo:exampleOne>
        <demo:comment>expected success</demo:comment>
        <demo:whenDone>
            var element = $(this);
            var html = element.html();
            element.html(html + successText);
        </demo:whenDone>
    </demo:exampleOne>

    <demo:exampleTwo>
        <demo:comment>expected success but fails as this example uses r:script and runs after example 3 (which breaks)</demo:comment>
        <demo:whenDone>
            var element = $(this);
            var html = element.html();
            element.html(html + successText);
        </demo:whenDone>
    </demo:exampleTwo>

    <demo:exampleOne>
        <demo:comment>should work but fails (javascript is html encode -> see javascript console)</demo:comment>
        <demo:whenDone>
            var element = $(this);
            var html = element.html();
            element.html(html + ' - success...');
        </demo:whenDone>
    </demo:exampleOne>

    <demo:exampleTwo>
        <demo:comment>should work but fails (javascript is html encode -> see javascript console)</demo:comment>
        <demo:whenDone>
            var element = $(this);
            var html = element.html();
            element.html(html + ' - success...');
        </demo:whenDone>
    </demo:exampleTwo>
</demo:main>

<r:layoutResources/>

</body>
</html>