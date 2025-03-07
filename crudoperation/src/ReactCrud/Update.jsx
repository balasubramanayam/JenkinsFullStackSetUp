import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";


const Update = () => {
  const [data, setData] = useState({ name: "", email: "",password:"" });
  const { id } = useParams();
  const nav = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8083/getById/${id}`)
      .then((response) => {
        setData(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [id]);

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8083/update/${id}`, data)
      .then((response) => {
        console.log(response.data);
        nav("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="form-container" style={{marginTop:'2%'}}>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Enter Name:</label>
          <input
            type="text"
            name="name"
            value={data.name}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Enter email:</label>
          <input
            type="text"
            name="email"
            value={data.email}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Enter password:</label>
          <input
            type="text"
            name="password"
            value={data.password}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="btn">Update</button>
      </form>
    </div>
  );
};

export default Update;
