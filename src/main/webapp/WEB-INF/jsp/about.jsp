<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

    <link rel="stylesheet" href="css/about.css">
    <link rel="stylesheet" href="css/general.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
</head>
<body id="body">
<jsp:include page="header.jsp"/>
<main>
    <section id="about">
        <h1>About SmartShop</h1>
        <div class="about-part">
            <div class="about-img">
                <img class="img-about"
                     src="https://static.wixstatic.com/media/c837a6_57c256d2c7474590a3f295bad576b0a1~mv2.jpg/v1/fill/w_801,h_843,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/c837a6_57c256d2c7474590a3f295bad576b0a1~mv2.jpg">
            </div>
            <div class="about-text">
                <p>This is a great space to write a long text about your company and your services.
                    You can use this space to go into a little more detail about your company.
                    Talk about your team and what services you provide.
                    Tell your visitors the story of how you came up with the idea for your business
                    and what makes you different from your competitors. Make your company stand out
                    and show your visitors who you are.</p><br>
                <p>
                    Suspendisse a bibendum turpis.
                    Donec vestibulum suscipit hendrerit.
                    Aenean quis lacus eros. Donec faucibus ante pulvinar sem feugiat luctus.
                    Fusce convallis, justo et venenatis placerat, lacus enim varius sapien, at consequat purus mi in
                    est. Integer et lorem ex.
                    In congue urna in vehicula viverra. Quisque vitae dolor quis nibh suscipit ullamcorper id nec arcu.
                    Nullam a neque hendrerit, malesuada neque vel,
                    sodales risus. Suspendisse pellentesque nisi vel lorem facilisis egestas.
                    Vestibulum cursus sit amet neque fermentum lacinia.
                </p>
            </div>
        </div>
    </section>
    <section id="career">
        <h2>Careers</h2>
        <p class="postings">Check out our job postings & opportunities waiting for you</p>
        <form:form action="/about" method="post" class="career-form" modelAttribute="careerForm">
            <div class="input-blocks">
                <div class="about-input-block">
                    <p>First Name</p>
                    <form:input path="first_name" name="fname" type="text" class="input" />
                </div>
                <div class="about-input-block">
                    <p>Last Name</p>
                    <form:input path="last_name" name="lname" type="text" class="input" />
                </div>
            </div>
            <div class="input-blocks">
                <div class="about-input-block">
                    <p>Email *</p>
                    <form:input path="email" name="email" type="email" class="input" />
                </div>
                <div class="about-input-block">
                    <p>Phone</p>
                    <form:input path="phone" name="phone" type="text" class="input" />
                </div>
            </div>
            <div class="input-blocks">
                <div class="about-input-block">
                    <p>Position You Apply For</p>
                    <form:select path="position" name="pos" class="input" id="positions">
                        <option value="In-store Sales">In-store Sales</option>
                        <option value="Store Leadership">Store Leadership</option>
                        <option value="In-store Operations">In-store Operations</option>
                        <option value="Wearhouse & Logistics">Wearhouse & Logistics</option>
                        <option value="eCommerce">eCommerce</option>
                    </form:select>
                </div>
                <div class="about-input-block">
                    <p>Link to Resume</p>
                    <form:input path="link" name="link" type="text" class="input" />
                </div>
            </div>
            <input type="submit" value="Submit" class="submit">
        </form:form>
    </section>
</main>
<jsp:include page="footer.jsp"/>
<script src="js/general.js"></script>
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