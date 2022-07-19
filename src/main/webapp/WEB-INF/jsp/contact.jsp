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
    <link rel="stylesheet" href="/css/contact.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

    <link rel="stylesheet" href="/css/general.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body id="body">
<jsp:include page="header.jsp"/>
<main>
    <section id="contact">
        <h1>Get in Touch</h1>
        <div class="parts">
            <div class="part1">
                <div class="work-time">
                    <h4>Work time</h4>
                    <p>Mon - Fri: 8am - 8pm</p>
                    <p>Saturday: 9am - 5pm</p>
                    <p>Sunday: 8am - 1pm</p>
                </div>
                <div class="location">
                    <h4>Store Location</h4>
                    <p>500 Terry Francois Street</p>
                    <p>San Francisco, CA 94158</p>
                    <p>info@mysite.com</p>
                    <p>123-456-7890</p>
                </div>
            </div>
            <div class="part2">
                <div class="form-text">
                    <h4>We're here to help!</h4>
                    <p>Fill out the form with any quarry on your mind and we'll get back to you as soon as possible</p>
                </div>
                <form action="/contact" method="post">
                    <div class="input-blocks">
                        <div class="about-input-block mar">
                            <p>First Name</p>
                            <input name="fname" type="text" class="input">
                        </div>
                        <div class="about-input-block">
                            <p>Last Name</p>
                            <input name="lname" type="text" class="input">
                        </div>
                    </div>
                    <div class="input-blocks">
                        <div class="about-input-block mar">
                            <p>Email *</p>
                            <input name="email" type="email" class="input" required/>
                        </div>
                        <div class="about-input-block">
                            <p>Subject</p>
                            <input name="subject" type="text" class="input">
                        </div>
                    </div>
                    <div class="area">
                        <p>Message</p>
                        <textarea name="message" class="input textarea"></textarea>
                    </div>
                    <input type="submit" name="form" value="Submit" class="submit">
                </form>
            </div>
        </div>
    </section>
    <section id="letters">
        <h1>Newsletter</h1>
        <p>Sign up to receive updates on new arrivals and special offers</p>
        <form action="/contact" method="post">
            <input class="email-news" name="email" type="email" placeholder="Your email here">
            <input class="email-submit" name="subs" type="submit" value="Subscribe">
        </form>
    </section>
</main>
<jsp:include page="footer.jsp"/>
<script src="/js/general.js"></script>
</body>
<script type="text/javascript">
    window.onload = function () {
        var al = "${result}";
        if (al !== "") {
            alert(al);
        }
    }
</script>
</html>