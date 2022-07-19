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
    <link rel="stylesheet" href="/css/success.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

</head>
<body>
<header>
    <nav>
        <a href="/" id="logo"><p class="logo">SmartShop</p></a>
        <p>Questions by phone:<br><span class="phone">0 800 800 800</span></p>
    </nav>
</header>
<main>
    <div class="success-container">
        <h1>Payment successful!</h1>
        <div class="correct-container">
            <img src="/img/correct.png" width="64" height="64">
        </div>
        <div class="success-text">
            <div class="success-child">
                <p class="general-success">Bank</p>
                <p class="certain-success">${transaction.bank}</p>
                <div style="clear: both;"></div>
            </div>
            <div class="success-child">
                <p class="general-success">Email</p>
                <p class="certain-success">${transaction.email}</p>
                <div style="clear: both;"></div>
            </div>
            <div class="success-child">
                <p class="general-success">Transaction Id</p>
                <p class="certain-success">${transaction.id}</p>
                <div style="clear: both;"></div>
            </div>
            <div class="success-child">
                <p class="general-success bold">Amount paid</p>
                <p class="certain-success bold">${transaction.amount}$</p>
                <div style="clear: both;"></div>
            </div>
        </div>
        <a href="/"><button class="home-return">Home page</button></a>
    </div>
</main>
<footer>
    <div class="copyright">
        <p>2022 &copy; by SmartShop. All rights are reserved</p>
    </div>
</footer>
</body>
</html>