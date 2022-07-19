const e = React.createElement;

class Checkout extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: false,
            cardInput: false,

            name: "",
            email: "",
            city: "",

            cvv: "",

            areaCode: "",
            prefix: "",
            suffix: "",

            phone1: "",
            phone2: "",
            phone3: "",

            part1: "",
            part2: "",
            part3: "",
            part4: "",
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        // this.handleCard = this.handleCard.bind(this);
        this.handleData = this.handleData.bind(this);
        this.setInputRef = this.setInputRef.bind(this);

        this.handleChange = this.handleChange.bind(this);

        this.handleCardChange = this.handleCardChange.bind(this);

        this.handlePhoneChange = this.handlePhoneChange.bind(this);
        this.submitForm1 = this.submitForm1.bind(this);
        this.submitForm2 = this.submitForm2.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    // handleCard() {
    //     if (!this.state.data)
    //         this.setState({data: true})
    //     else {
    //         this.setState({data: false})
    //     }
    //
    // }

    handleData() {
        this.setState({cardInput: true})
    }

    setInputRef(name, element) {
        this[name] = element;
    }

    handleChange({target: {name, value}}) {
        let valueChanged = false;
        this.setState(prevState => {
            const nextValue = value.replace(/[^\d]/g, '');
            const changedValue = prevState[name];
            if (changedValue.length !== nextValue.length) valueChanged = true;

            return {[name]: nextValue}
        }, () => {
            if (valueChanged) this.handleFocus(name)
        });
    };

    handleFocus(name) {
        const {areaCode, prefix, suffix} = this.state;
        const areaCodeFilled = areaCode.length === 3;
        const prefixFilled = prefix.length === 2;

        if (areaCodeFilled && name === "areaCode") {
            this.prefix.focus();
            this.prefix.selectionEnd = 0;
        } else if (prefixFilled && name === "prefix") {
            this.suffix.focus();
            this.suffix.selectionEnd = 0;
        }
    }

    handleCardChange({target: {name, value}}) {
        let valueChanged = false;
        this.setState(prevState => {
            const nextValue = value.replace(/[^\d]/g, '');
            const changedValue = prevState[name];
            if (changedValue.length !== nextValue.length) valueChanged = true;

            return {[name]: nextValue}
        }, () => {
            if (valueChanged) this.handleFocusCard(name)
        });
    }

    handleFocusCard(name) {
        const {part1, part2, part3, part4} = this.state;
        const part1Filled = part1.length === 4;
        const part2Filled = part2.length === 4;
        const part3Filled = part3.length === 4;

        if (part1Filled && name === "part1") {
            this.part2.focus();
            this.part2.selectionEnd = 0;
        } else if (part2Filled && name === "part2") {
            this.part3.focus();
            this.part3.selectionEnd = 0;
        } else if (part3Filled && name === "part3") {
            this.part4.focus();
            this.part4.selectionEnd = 0;
        }
    }

    handlePhoneChange({target: {name, value}}) {
        let valueChanged = false;
        this.setState(prevState => {
            const nextValue = value.replace(/[^\d]/g, '');
            const changedValue = prevState[name];
            if (changedValue.length !== nextValue.length) valueChanged = true;

            return {[name]: nextValue}
        }, () => {
            if (valueChanged) this.handlePhone(name)
        });
    }

    handlePhone(name) {
        const {phone1, phone2, phone3} = this.state;
        const phone1Filled = phone1.length === 3;
        const phone2Filled = phone2.length === 3;

        if (phone1Filled && name === "phone1") {
            this.phone2.focus();
            this.phone2.selectionEnd = 0;
        } else if (phone2Filled && name === "phone2") {
            this.phone3.focus();
            this.phone3.selectionEnd = 0;
        }
    }

    submitForm1(event){
        if (!this.state.data)
            this.setState({data: true})
        else {
            this.setState({data: false})
        }
        event.preventDefault();
    }

    submitForm2(event){
    }

    render() {
        if (this.state.data) {
            return (
                <form className="data-form" method="post" action="/checkout" onSubmit={this.submitForm2}>
                    <input type="hidden" value={this.state.name} name="name"/>
                    <input type="hidden" value={this.state.phone1} name="phone1"/>
                    <input type="hidden" value={this.state.phone2} name="phone2"/>
                    <input type="hidden" value={this.state.phone3} name="phone3"/>
                    <input type="hidden" value={this.state.email} name="email"/>
                    <input type="hidden" value={this.state.city} name="city"/>
                    <input type="hidden" value={window.getTotal()} name="total"/>
                    <h4>2. Credit card</h4>
                    <div className="text-container">
                        <p>Card number</p>
                        <div className="input-card">
                            <input
                                ref={node => this.setInputRef("part1", node)}
                                className="input suffix"
                                type="text"
                                name="part1"
                                placeholder="xxxx"
                                value={this.state.part1}
                                onChange={this.handleCardChange}
                                maxLength="4"
                            />
                            <div className="dash">-</div>
                            <input
                                ref={node => this.setInputRef("part2", node)}
                                className="input suffix"
                                type="text"
                                name="part2"
                                placeholder="xxxx"
                                value={this.state.part2}
                                onChange={this.handleCardChange}
                                maxLength="4"
                            />
                            <div className="dash">-</div>
                            <input
                                ref={node => this.setInputRef("part3", node)}
                                className="input suffix"
                                type="text"
                                name="part3"
                                placeholder="xxxx"
                                value={this.state.part3}
                                onChange={this.handleCardChange}
                                maxLength="4"
                            />
                            <div className="dash">-</div>
                            <input
                                ref={node => this.setInputRef("part4", node)}
                                className="input suffix"
                                type="text"
                                name="part4"
                                placeholder="xxxx"
                                value={this.state.part4}
                                onChange={this.handleCardChange}
                                maxLength="4"
                            />
                        </div>
                    </div>
                    <div className="text-container">
                        <p>Expiry date</p>
                        <div className="input-date">
                            <input
                                ref={node => this.setInputRef("prefix", node)}
                                className="input prefix"
                                type="text"
                                name="prefix"
                                placeholder="mm"
                                value={this.state.prefix}
                                onChange={this.handleChange}
                                maxLength="2"
                            />
                            <div className="dash">/</div>
                            <input
                                ref={node => this.setInputRef("suffix", node)}
                                className="input suffix"
                                type="text"
                                name="suffix"
                                placeholder="yyyy"
                                value={this.state.suffix}
                                onChange={this.handleChange}
                                maxLength="4"
                            />
                        </div>
                    </div>
                    <div className="text-container">
                        <p>Security code</p>
                        <input
                            onChange={this.handleInputChange}
                            name="cvv" type={"password"}
                            className="text-field cvv"
                            value={this.state.cvv}
                            maxLength="3"
                            placeholder="xxx"></input>
                    </div>
                    <input onClick={this.handleCard} className="back" value={"Back"}/>
                    <input onClick={this.handleData} type="submit"
                           disabled={!this.state.part1 || !this.state.part2 || !this.state.part3
                               || !this.state.part4 || !this.state.prefix || !this.state.suffix || !this.state.cvv}
                           className="submit" value="Pay"/>
                </form>
            )
        } else {
            return (
                <form className="data-form" method="post" action="/checkout" onSubmit={this.submitForm1}>
                    <h4>1. Contact data</h4>
                    <div className="text-container">
                        <p>Name</p>
                        <input
                            onChange={this.handleInputChange}
                            name="name" type={"text"}
                            className="text-field"
                            value={this.state.name}
                            placeholder="Your name"></input>
                    </div>
                    <div className="text-container">
                        <p>Phone</p>
                        <div className="input-date input-phone">
                            <input type="text" id="region" value="+38" disabled/>
                            <div className="parenthesis" style={{marginLeft: 10}}>&#40;</div>
                            <input
                                className="input area-code"
                                type="text"
                                name="phone1"
                                placeholder="xxx"
                                value={this.state.phone1}
                                onChange={this.handlePhoneChange}
                                maxLength="3"
                            />
                            <div className="parenthesis" style={{marginRight: 2}}>&#41;</div>
                            <input
                                ref={node => this.setInputRef("phone2", node)}
                                className="input prefix"
                                type="text"
                                name="phone2"
                                placeholder="xxx"
                                value={this.state.phone2}
                                onChange={this.handlePhoneChange}
                                maxLength="3"
                            />
                            <div className="dash">-</div>
                            <input
                                ref={node => this.setInputRef("phone3", node)}
                                className="input suffix"
                                type="text"
                                name="phone3"
                                placeholder="xxxx"
                                value={this.state.phone3}
                                onChange={this.handlePhoneChange}
                                maxLength="4"
                            />
                        </div>
                        <div class="line"></div>
                    </div>
                    <div className="text-container">
                        <p>Email</p>
                        <input
                            onChange={this.handleInputChange}
                            name="email" type={"email"}
                            className="text-field"
                            value={this.state.email}
                            placeholder="Input email"></input>
                    </div>
                    <div className="text-container">
                        <p>City</p>
                        <input
                            onChange={this.handleInputChange}
                            name="city" type={"text"}
                            className="text-field"
                            value={this.state.city}
                            placeholder="Input city"></input>
                    </div>
                    <input type="submit"
                           className="submit" value="Next"
                           disabled={!this.state.city || !this.state.name || !this.state.email ||
                               !this.state.phone1 || !this.state.phone2 || !this.state.phone3}/>
                </form>
            )
        }
    }
}

const domContainer = document.querySelector('#checkout-form');
ReactDOM.render(e(Checkout), domContainer);