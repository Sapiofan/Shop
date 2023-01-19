<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Smart shop | Technics</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <meta
            name="description"
            content="A project to train with Spring and React frameworks"
    />

    <meta property="og:title" content="Smartshop"/>
    <meta property="og:type" content="website"/>
    <meta
            property="og:description"
            content="A project to try to create qualitative site."
    />
    <link rel="icon"
          href="https://ru.seaicons.com/wp-content/uploads/2015/10/Flat-TV-icon.png">
    <link rel="stylesheet" href="css/help.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

    <link rel="stylesheet" href="css/general.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body id="body">
<jsp:include page="header.jsp"/>
<main class="main">
    <section id="shipping">
        <h1>Shipping & Returns</h1>
        <div class="text-block">
            <h3>Shipping Policy</h3>
            <p>I'm a Shipping Policy section.
                I'm a great place to update your customers about your shipping methods,
                packaging and costs. Use plain, straightforward language to build trust and make sure
                that your customers stay loyal!</p><br>
            <p>I'm the second paragraph in your Shipping Policy section.
                Click here to add your own text and edit me.
                It's easy. Just click "Edit Text" or double click me to add details about your
                policy and make changes to the font.
                I'm a great place for you to tell a story and let your users know a little more about you.</p>
        </div>
        <div class="text-block">
            <h3>Returns and Exchange Policy</h3>
            <p>I'm a Shipping Policy section.
                I'm a great place to update your customers about your shipping methods,
                packaging and costs. Use plain, straightforward language to build trust and make sure
                that your customers stay loyal!</p><br>
            <p>I'm the second paragraph in your Shipping Policy section.
                Click here to add your own text and edit me.
                It's easy. Just click "Edit Text" or double click me to add details about your
                policy and make changes to the font.
                I'm a great place for you to tell a story and let your users know a little more about you.</p>
        </div>
    </section>
    <section id="terms">
        <h1>Terms & Conditions</h1>
        <div class="text-block">
            <h3>Customer Care</h3>
            <p>I'm a Customer Care section.
                I'm a great place to update your customers about your shipping methods,
                packaging and costs. Use plain, straightforward language to build trust and make sure
                that your customers stay loyal!</p><br>
            <p>I'm the second paragraph in your Shipping Policy section.
                Click here to add your own text and edit me.
                It's easy. Just click "Edit Text" or double click me to add details about your
                policy and make changes to the font.
                I'm a great place for you to tell a story and let your users know a little more about you.</p>
        </div>
        <div class="text-block">
            <h3>Privacy & Safety</h3>
            <p>I'm a Privacy & Safety policy section.
                I'm a great place to update your customers about your shipping methods,
                packaging and costs. Use plain, straightforward language to build trust and make sure
                that your customers stay loyal!</p><br>
            <p>I'm the second paragraph in your Shipping Policy section.
                Click here to add your own text and edit me.
                It's easy. Just click "Edit Text" or double click me to add details about your
                policy and make changes to the font.
                I'm a great place for you to tell a story and let your users know a little more about you.</p>
        </div>
    </section>
    <section id="faq">
        <h1>Frequently Asked Questions</h1>
        <div class="questions">
            <c:forEach items="${faqs}" var="faq">
                <div class="question">
                    <button type="button" class="collapsible1">${faq.question}</button>
                    <div class="content1">
                        <p>${faq.answer}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</main>
<jsp:include page="footer.jsp"/>
<script src="js/help.js"></script>
<script src="js/general.js"></script>
</body>
</html>