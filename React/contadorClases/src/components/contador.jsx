import React,{Component} from "react";
export default class contador extends Component {
    render() {
      return (
        <div className="counter">
          <h1>{this.props.valor}</h1>

        </div>
      );
    }
  }
  