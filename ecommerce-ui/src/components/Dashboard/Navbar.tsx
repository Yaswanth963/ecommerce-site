import * as React from 'react';

interface NavbarProps {
    title: String,
    dashboardNavbar: String
}

const Navbar: React.FunctionComponent<NavbarProps> = (props) => {
    return (
        <div className='container-fluid border'>
            {props.title}
        </div>
    );
};

export default Navbar;
