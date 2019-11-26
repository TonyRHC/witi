import React, { PureComponent } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';
import axios from 'axios';
import BarLoader from 'react-spinners/BarLoader';


export default class MoreDetailsLineGraph extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      data:[],
      apiLoad: 0
    };
  }

  componentWillMount() {
    axios.get(process.env.REACT_APP_REST_API + '/viewDetails/getGraphByStrategyId', {
      params: {
        id: this.props.id
      }
    })
    .then(response => {
      this.setState({
        data: response.data,
        apiLoad: 1
      });
    })
    .catch(error => {
      console.log(error);
    });
  }

  render() {
    return (

      <div>
        {this.state.apiLoad === 1 ? 
          <LineChart
            width={400}
            height={250}
            data={this.state.data}
            margin={{
              top: 0, right: 0, left: 0, bottom: 0,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey="P/L" stroke="#4db6ac" activeDot={{ r: 8 }} />
          </LineChart>
          : <BarLoader color='white'/>
        }
      </div>
    );
  }
}
