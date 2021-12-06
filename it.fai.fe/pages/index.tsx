import React from 'react';
import css from 'styled-jsx/css';
import { withNamespaces } from '@app/i18nnext';
import { TotPage } from '@app/components/TotPage';
import { IBasicPageProps } from '@app/components/Types';
import { getAllMagazzino, faiImportCsv } from '@app/api/log';

const styles = css`
  @import "static/styles/scss-in-theme/bootstrap/functions";
  @import "static/styles/scss-in-theme/bootstrap/variables";
  @import "static/styles/scss-in-theme/mixins";

  :global(body) {
    padding: 0px;
    height: 100%;
  }
  :global(#__next) {
    height: 100%;
  }
  :global(#__next > div) {
    height: 100%;
  }

    :global(.page) {
        display: flex !important;
        flex-direction: column !important;
    }
    :global(.p-card) {
        display: flex !important;
        flex-direction: column !important;
        flex: 1 !important;
    }

  :global(.page-content) {
    padding: 0px;
    padding: 0px;
    height: 101%;
    background: #f3f3f4;
  }
  .dashboard {
    .check-in-box {
      .action{
        a {
          display:block;
          float:left;
        }
        span {
          float:right;
          width:unset;
        }
      }
    }
    .panel-hide {
      opacity: 0;
      visibility: hidden;
    }
    .panel-show {
      opacity: 1;
      visibility: unset;
    }
  }
  :global(.g-info-window h3) {
    margin-top: 10px !important;
  }

  :global(div[data-test='doctor-card-info-rating'] > div) {
    align-items: center !important;
  }
  :global(div[data-test='doctor-card-info-location']) {
    font-size: 17px !important;
    color: #111 !important;
    font-weight: 400 !important;
  }

  :global(a[data-test='doctor-card-info-name'] h3) {
    font-weight: 500 !important;
    font-size: 18px !important;
  }

  *[data-test='doctor-card-info-rating-review'] {
    color: #333;
    font-size: 14px;
  }
  *[data-test='regular-text-section'] {
    width: 90%;
  }

  :global(div[data-test='doctor-card-info-rating-number']) {
    margin-left: 5px !important;
  }

  @include media-breakpoint-down(sm){
    :global(#toast-container) {
      top: 76px;
      left: 10px;
      right: 10px
      & :global(.toast-info) {
        width: 100%;
      }
    }
  }

  .lfZhxT {
      -webkit-box-align: center;
      align-items: center;
      box-sizing: border-box;
      display: flex;
      font: 13px / 1 sharp-sans-semibold, Arial, sans-serif;
      height: 32px;
      -webkit-box-pack: center;
      justify-content: center;
      padding: 0px;
      text-align: center;
      text-decoration: none;
      white-space: nowrap;
      animation: 0.1s ease-out 0s 1 normal forwards running ehLtDs;
      cursor: pointer;
      background-color: rgb(255, 240, 75);
      border: 1px solid rgb(255, 240, 75);
      color: rgb(0, 35, 75);
      margin-top: 8px;
      width: 70px;
      margin-left: 5px;
      text-align: center;
  }
    .aqxfE {
        -webkit-box-align: center;
        align-items: center;
        box-sizing: border-box;
        display: flex;
        font: 13px / 1 sharp-sans-semibold, Arial, sans-serif;
        height: 32px;
        -webkit-box-pack: center;
        justify-content: center;
        padding: 0px;
        text-align: center;
        text-decoration: none;
        white-space: nowrap;
        animation: 0.1s ease-out 0s 1 normal forwards running ehLtDs;
        background-color: rgb(255, 240, 75);
        color: rgb(0, 35, 75);
        border: 0px;
        cursor: pointer;
        width: 100%;
        margin-top: 8px;
    }
    :global(.qDvlK > svg) {
        width: 20px;
        height: 20px;
        display: inline-flex;
        -webkit-box-align: center;
        align-items: center;
        margin-right: 7px;
        vertical-align: middle;
        background-repeat: no-repeat;
        position: initial;
    }
    :global(.qDvlK) {
        display: inline-flex;
        -webkit-box-align: center;
        align-items: center;
        color: rgb(43, 191, 237);
        font: 15px / 24px sharp-sans-semibold, Arial, sans-serif;
    }

    :global(.tree-view_arrow) {
        -moz-transition: all 0.1s;
        -o-transition: all 0.1s;
        -ms-transition: all 0.1s;
        -webkit-transition: all 0.1s;
        transition: all 0.1s;
    }

    :global(.tree-view_arrow-empty) {
      color: yellow;
    }

    :global(.tree-view_arrow) {
        width: 15px !important;
    }

    :global(.tree-view_item > span > i) {
        margin-right: 5px !important;

    }
    :global(.tree-view_item) {
        display: flex;
        flex-direction: row;
        align-items: center;
        cursor: pointer;
        flex-wrap: nowrap;
        white-space: nowrap;

    }
    :global(.tree-view_children) {
        margin-top: 5px !important;
        margin-left: 20px !important;
    }

    :global(.tree-view_arrow:after) {
        font-size: 22px;
    }
    :global(.btn-link-app) {
        background-color: rgb(38, 166, 154);
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 5px;
        border-radius: 2.5px;
        font-weight: 400;
        cursor: pointer;
        width: 180px;
    }
    :global(.btn-edit) {
        background-color: #2196F3;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 5px;
        border-radius: 2.5px;
        font-weight: 400;
        cursor: pointer;
        width: 80px;
        margin-right: 5px;
    }
    :global(.btn-remove) {
        background-color: #ec407a;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 5px;
        border-radius: 2.5px;
        font-weight: 400;
        cursor: pointer;
        width: 100px;
    }
    .chartWithOverlay {
       position: relative;
       width: 700px;
    }
    .overlay {
       width: 200px;
       height: 200px;
       position: absolute;
       top: 60px;   /* chartArea top  */
       left: 180px; /* chartArea left */
}
.db-top-head {
    background: #f1f1f1;
    height: 50px;
    margin: 5px;
    border-radius: 5px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 15px;
    font-size: 17px;
    font-weight: 400;
}
.db-top-content {
    height: 150px;
    margin: 5px;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 15px;
    font-size: 17px;
    font-weight: 400;
    .db-top-item {
        flex: 1;
        border-left: 3.5px solid #000;
        padding: 0px 15px;
        display: flex;
        flex-direction: column;
        color: #000;
        .db-top-item-title {
            font-size: 17px;
        }
        .db-top-item-content {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            font-size: 37px;
            font-weight: 600;
        }
        .db-top-item-desc {
            font-size: 14px;
        }
        &.db-top-item-1 .db-top-item-content {
        }
        &.db-top-item-2 .db-top-item-content {
            color: #00cc66;
        }
        &.db-top-item-3 .db-top-item-content {
            color: #00cc66;
        }
        &.db-top-item-4 .db-top-item-content {
        }
        &.db-top-item-5 {
            flex: 2;
            .db-top-item-bt {
                flex: 1;
                padding-right: 25px;
                white-space: nowrap;
                font-size: 13px;
                &.odd {
                    color: #00cc66;
                }
            }
            .db-top-item-desc {
                display: flex;
                flex-direction: row;
            }
        }
    }
}
.db-mid-head {
    background: #cce7fe;
    color: #1c4a83;
    height: 50px;
    margin: 5px;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 15px;
    font-size: 25px;
    font-weight: 500;
    align-items: center;
}
.db-mid-content {
    //background: #ffe6cc;
    //color: #1c4a83;
    display: flex;
    flex-direction: column;
    //padding: 15px;
    font-size: 15px;
    //font-weight: 500;
    margin: 15px;
    //border: 2px solid #e5b548;
    border-radius: 2px;
    min-height: 250px;
}

    :global(.btn-link-app) {
        background-color: rgb(38, 166, 154);
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 5px;
        border-radius: 2.5px;
        font-weight: 400;
        cursor: pointer;
        width: 180px;
    }
    :global(.btn-edit) {
        background-color: #2196F3;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 5px;
        border-radius: 2.5px;
        font-weight: 400;
        cursor: pointer;
        width: 80px;
        margin-right: 5px;
    }
    :global(.btn-remove) {
        background-color: #ec407a;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 5px;
        border-radius: 2.5px;
        font-weight: 400;
        cursor: pointer;
        width: 100px;
    }
    .chartWithOverlay {
       position: relative;
       width: 700px;
    }
    .overlay {
       width: 200px;
       height: 200px;
       position: absolute;
       top: 60px;   /* chartArea top  */
       left: 180px; /* chartArea left */
    }
    :global(.selected) {
        background-color: coral;
    }
    :global(.multi-select-cid) {
      width: 49%;
      font-size: 14px;
    }
    :global(.checkbox-container) {
      display: flex;
      flexDirection: row;
      align-items: center;
      margin-right: 30px;
      color: #282828;
      >input {
        height: 16px;
        width: 16px;
      }
      >span {
        font-size: 14px;
        font-weight: 500;
      }
    }
  :global(.table-th) {
    background: #f2f3ff !important;
    color: #000000 !important;
    font-weight: 500 !important;
    fontSize: 14px !important;
    width: 180px !important;
    maxWidth: 350px !important;
    padding: 10px !important;
    padding-left: 30px !important;
  }
  :global(.table-td) {
    padding: 10px !important;
    padding-left: 30px !important;
    vertical-align: middle !important;
  }
  :global(.site-title) {
    color: #000000;
    font-size: 20px;
    margin: 15px;
    background: aliceblue;
    height: 60px;
    align-items: center;
    display: flex;
    border-radius: 10px;
    padding-left: 15px;
    font-weight: 500;
  }
  :global(.button-btn) {
    border: unset;
    outline: unset;
    display: flex;
    border-radius: 5px;
    height: 40px;
    cursor: pointer;
    justify-content: center;
    align-items: center;
    background-color: #042ee1;
    font-size: 14px;
    font-weight: 500;
    color: #FFFFFF;
    width: 150px;
    text-transform: uppercase;
    transition: 0.2s;
    &:hover {
      background-color: #03114e;
      color: #fffe00;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.5);
      transition: 0.2s;
    }
  }
  :global(.mark-btn) {
      outline: unset;
      border: unset;
      white-space: nowrap;
      width: 180px !important;
      border: unset;
      outline: unset;
      display: flex;
      border-radius: 5px;
      height: 40px;
      cursor: pointer;
      justify-content: center;
      align-items: center;
      background-color: #042ee1;
      font-size: 14px;
      font-weight: 500;
      color: #FFFFFF;
      width: 150px;
      text-transform: uppercase;
      transition: 0.2s;
  }
  :global(.button-icon) {
    display: flex;
    height: 40px;
    width: 40px;
    cursor: pointer;
    justify-content: center;
    align-items: center;
    background-color: transparent;
    font-size: 25px;
    color: #000000;
    border-radius: 50%;
    transition: 0.15s;
    &:hover {
      border-radius: 50%;
      color: #fffe00;
      background: #03114e;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.5);
      transition: 0.15s;
    }
  }
:global(.pi-status) {
    width: 100px;
    color: #fff;
    background: green;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 2px;
    padding: 2.5px;
}
:global(.pi-action-td) {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: flex-end;
    padding-right: 50px !important;
    font-size: 20px;
}
`;

interface IDashboardPageState {

}

class Homepage extends React.Component<IBasicPageProps, IDashboardPageState> {
    static getInitialProps = async (args) => {
        let { asPath, initialState: {} } = args;
        const rpMagazzino = await getAllMagazzino();
        return {
            namespacesRequired: ['common'],
            asPath,
            key: Date.now(),
            useTemplate: true,
            loginRequire: false,
            magazzino: rpMagazzino.response || [],
        };
    }
    state = {
        ...this.props,
    };
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <TotPage title={'Import Order'}>
                <style jsx>{styles}</style>
                <div className="db-top-head mt-30">
                    <span>Import Order</span>
                </div>
                <div>
                    <div style={{ display: 'flex', flexDirection: 'row', marginLeft: '15px', alignItems: 'center' }}>
                        <span className="mr-15" style={{ fontSize: '16px', color: '#000000' }}>{'Magazzino: '}</span>
                        <select
                            style={{ outline: 'unset', width: '200px', height: '35px' }}
                            className="select-group-device"
                            value={this.state.acronym || ''}
                            onChange={(event) => {
                                this.setState({ acronym: event.target.value });
                            }}
                        >
                            <option value="">{'Select'}</option>
                            {this.state.magazzino.map((item, index) => (
                                <option key={index} value={item?.acronym}>{item?.descr}</option>
                            ))}
                        </select>
                    </div>
                    <input id="upload-fai-csv" className="ml-15 mt-15" type="file" />
                    <div style={{ display: 'flex', flexDirection: 'row', marginLeft: '15px', alignItems: 'center', marginTop: '15px' }}>
                        <div className="button-btn w-80"
                             onClick={async () => {
                                 if (!this.state.acronym) {
                                     alert('Please choose a Magazzino!');
                                     return;
                                 }
                                 const file = document.getElementById('upload-fai-csv').files[0];
                                 if (!file) {
                                     alert('Please choose a file!');
                                     return;
                                 }
                                 const formData = new FormData();
                                 formData.append('file', file);
                                 const rp = await faiImportCsv(file, this.state.acronym);
                                 if (rp.success) {
                                     alert('File upload successfully!');
                                     return;
                                 }
                             }}
                        >
                            <span>{'IMPORT'}</span>
                        </div>
                        <div style={{ marginLeft: '15px' }} className="button-btn w-80">
                            <span>{'CANCEL'}</span>
                        </div>
                    </div>
                </div>
            </TotPage>
        );
    }
}

export default withNamespaces('common')(Homepage);
