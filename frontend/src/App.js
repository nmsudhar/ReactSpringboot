import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component <Props> {

    componentDidMount() {
        setInterval(this.btcCurrencies, 1200);
        setInterval(this.etcCurrencies, 2200);
        setInterval(this.ltcCurrencies, 3200);
    }

    btcCurrencies = () => {
        fetch('/api/btcCurrencies')
            .then(response => response.text())
            .then(btcMessage => {
                this.setState({btcMessage: JSON.parse(btcMessage)})
            });
    }

    etcCurrencies = () => {
        fetch('/api/etcCurrencies')
            .then(response => response.text())
            .then(etcMessage => {
                this.setState({etcMessage: JSON.parse(etcMessage)});
            });
    };
    ltcCurrencies = () => {
        fetch('/api/ltcCurrencies')
            .then(response => response.text())
            .then(ltcMessage => {
                this.setState({ltcMessage: JSON.parse(ltcMessage)});
            });
    };

    constructor(props) {
        super(props);
        this.state = {
            btcMessage: {},
            etcMessage: {},
            ltcMessage: {}
        };
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                </header>
                <p className="App-intro">
                    Crypto Currencies Data Analyser
                </p>
                <table className="table table-hover" align="center" border="1">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Buy</th>
                        <th>Time</th>
                        <th>Sell</th>
                        <th>Profit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            {this.state.btcMessage.currencyName}
                        </td>
                        <td>
                            {this.state.btcMessage.updatedDate}
                        </td>
                        <td>
                            {this.state.btcMessage.minimumPriceTime}
                        </td>
                        <td>
                            {this.state.btcMessage.minimumPriceValue}
                        </td>
                        <td>
                            {this.state.btcMessage.maximumPriceTime}
                        </td>
                        <td>
                            {this.state.btcMessage.maximumPriceValue}
                        </td>
                        <td>
                            {this.state.btcMessage.profit}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            {this.state.etcMessage.currencyName}
                        </td>
                        <td>
                            {this.state.etcMessage.updatedDate}
                        </td>
                        <td>
                            {this.state.etcMessage.minimumPriceTime}
                        </td>
                        <td>
                            {this.state.etcMessage.minimumPriceValue}
                        </td>
                        <td>
                            {this.state.etcMessage.maximumPriceTime}
                        </td>
                        <td>
                            {this.state.etcMessage.maximumPriceValue}
                        </td>
                        <td>
                            {this.state.etcMessage.profit}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            {this.state.ltcMessage.currencyName}
                        </td>
                        <td>
                            {this.state.ltcMessage.updatedDate}
                        </td>
                        <td>
                            {this.state.ltcMessage.minimumPriceTime}
                        </td>
                        <td>
                            {this.state.ltcMessage.minimumPriceValue}
                        </td>
                        <td>
                            {this.state.ltcMessage.maximumPriceTime}
                        </td>
                        <td>
                            {this.state.ltcMessage.maximumPriceValue}
                        </td>
                        <td>
                            {this.state.ltcMessage.profit}
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
        );
    }
}

export default App;
