<#import "spring.ftl" as spring>
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

    <#assign css = "./static/css">
    <#assign js = "./static/js">
    <#assign img = "./static/img">

    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/about.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/general.css'/>"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
</head>
<body id="body">
<#include "header.ftl">
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
        <form action="/about" method="post" class="career-form" name="careerForm">
            <div class="input-blocks">
                <div class="about-input-block">
                    <p>First Name</p>
                    <@spring.formInput "careerForm.first_name" "required class='input'"/>
                </div>
                <div class="about-input-block">
                    <p>Last Name</p>
                    <@spring.formInput "careerForm.last_name" "required class='input'"/>
                </div>
            </div>
            <div class="input-blocks">
                <div class="about-input-block">
                    <p>Email *</p>
                    <@spring.formInput "careerForm.email" "required class='input'"/>
                </div>
                <div class="about-input-block">
                    <p>Phone</p>
                    <@spring.formInput "careerForm.phone" "required class='input'"/>
                </div>
            </div>
            <div class="input-blocks">
                <div class="about-input-block">
                    <p>Position You Apply For</p>
                    <#assign options = ['In-store Sales', 'Store Leadership', 'Wearhouse & Logistics',
                    'In-store Operations', 'eCommerce']>
                    <@spring.formSingleSelect "careerForm.position", options, "required class='input'" />

                </div>
                <div class="about-input-block">
                    <p>Link to Resume</p>
                    <@spring.formInput "careerForm.link" "required class='input'"/>
                </div>
            </div>
            <input type="submit" value="Submit" class="submit">
        </form>
    </section>
</main>
<#include "footer.ftl">
<script src="${js}/general.js"></script>
</body>
<script type="text/javascript">
    window.onload = function () {
        var al = "${result!""}";
        if (al !== "") {
            alert(al);
        }
    }
</script>
</html>